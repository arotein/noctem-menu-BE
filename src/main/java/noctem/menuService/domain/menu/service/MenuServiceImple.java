package noctem.menuService.domain.menu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.AppConfig;
import noctem.menuService.domain.menu.dto.*;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.menu.repository.IMenuRepository;
import noctem.menuService.domain.nutrition.entity.NutritionEntity;
import noctem.menuService.domain.nutrition.repository.INutritionRepository;
import noctem.menuService.domain.redis.RedisRepository;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.size.repository.ISizeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImple implements IMenuService {

    private final IMenuRepository iMenuRepository;
    private final ISizeRepository iSizeRepository;
    private final INutritionRepository nutritionRepository;
    private final String TEMPERATURE_POLICY = "ice";
    private final RedisRepository redisRepository;

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
        6. 소카테고리-메뉴 조회
        7. 장바구니 목록 조회
        8. 메뉴 하위 정보 상세 조회
        9. 소 카테고리 - 메뉴 조회(관리자용)
        10. 메뉴 검색 (이름)
     */

    // 1. 메뉴 등록
    @Override
    public MenuDto addMenu(MenuDto menuDto) {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        MenuEntity menuEntity = mapper.map(menuDto, MenuEntity.class); // MenuDto -> MenuEntity
        iMenuRepository.save(menuEntity);

        MenuDto resultMenuDto = mapper.map(menuEntity, MenuDto.class); // MenuEntity -> MenuDto

        return resultMenuDto;
    }


    // 2. 메뉴 수정
    @Override
    public MenuDto editMenu(Long menuId, MenuDto menuDto) throws Exception {

        Optional<MenuEntity> menuEntity = iMenuRepository.findById(menuId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (menuEntity.isPresent()) {
            MenuEntity menu = mapper.map(menuDto, MenuEntity.class); // MenuDto -> MenuEntity
            menu.setId(menuId); // 동일 id로 수정
            iMenuRepository.save(menu); // 엔터티 저장

            MenuDto resultMenuDto = mapper.map(menu, MenuDto.class); // MenuEntity -> MenuDto

            return resultMenuDto;
        } else {
            throw new Exception("해당하는 메뉴 id가 없습니다.");
        }
    }

    // 3. 메뉴 삭제
    @Override
    public MenuDto deleteMenu(Long menuId) throws Exception {

        Optional<MenuEntity> menuEntity = iMenuRepository.findById(menuId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (menuEntity.isPresent()) {
            menuEntity.get().setIsDeleted(true); // 삭제여부 true

            iMenuRepository.save(menuEntity.get());
            MenuDto menuDto = mapper.map(menuEntity, MenuDto.class);

            return menuDto;
        } else {
            throw new Exception("해당하는 메뉴 id가 없습니다.");
        }
    }

    // 4. 메뉴 전체 조회
    @Override
    public Iterable<MenuDto> getAllMenu() {

        List<MenuEntity> menuEntityList = iMenuRepository.findAll();
        List<MenuDto> menuDtoList = new ArrayList<>(); // 비어있는 menuDto 리스트 선언

        menuEntityList.forEach(menuEntity -> {

            if (!menuEntity.getIsDeleted())
                menuDtoList.add(new ModelMapper().map(menuEntity, MenuDto.class));
        });
        return menuDtoList;
    }

    // 5. 메뉴 단건 조회
    @Override
    public MenuDto getOneMenu(Long menuId) throws Exception {

        Optional<MenuEntity> menuEntity = iMenuRepository.findById(menuId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (menuEntity.isPresent() && menuEntity.get().getIsDeleted() == false) { // 논리삭제가 false인 것
            MenuDto menuDto = mapper.map(menuEntity, MenuDto.class); // MenuEntity -> MenuDto
            return menuDto;
        } else {
            throw new Exception("해당하는 메뉴 id가 없습니다.");
        }
    }

    // 6. 소카테고리-메뉴 조회
    @Override
    public List<MenuListResDto> getMenuList(Long categorySId) {

        String menuListRedis = redisRepository.getMenuList(categorySId);
        log.info(menuListRedis);
        log.info("test1");
        if (menuListRedis != null) {
            try{
                List<MenuListResDto> result = AppConfig.objectMapper().readValue(menuListRedis, new TypeReference<>() {
                });
                log.info("get getMenuList from redis");
                return result;
            } catch (JsonProcessingException e){
                log.info("JsonProcessingException in getMenuList");
                log.info(e.getMessage());
            }

        }
        List<MenuEntity> menuList = iMenuRepository.findMenuByCategoryS(categorySId);
        return menuList.stream().map(e ->
                        new MenuListResDto(e.getId(), e.getTemperatureEntityList(), e.getPrice(), TEMPERATURE_POLICY))
                .collect(Collectors.toList());
    }



    // 7-1. 장바구니 목록 조회 (리스트)
    @Override
    public List<CartAndOptionListResServDto> getMenuCartList(List<CartAndOptionsReqServDto> dto) {

//        List<Long> sizeIdList = dto.stream().map(e -> e.getSizeId()).collect(Collectors.toList());
//
//        List<SizeEntity> bySizeIdIn = iSizeRepository.findByIdIn(sizeIdList);
//
//        return bySizeIdIn.stream().map(e -> new CartAndOptionResServDto(e.getId(), e.getTemperatureEntity().getMenuName(),
//                e.getTemperatureEntity().getMenuEngName(), e.getTemperatureEntity().getMenuImg(),
//                e.getTemperatureEntity().getMenuEntity().getPrice() + e.getExtraCost()))
//                .collect(Collectors.toList());

        Map<CartAndOptionsReqServDto, CartAndOptionListResServDto> dtoMap = dto.stream()
                .collect(Collectors.toMap(Function.identity(), CartAndOptionListResServDto::new));
        // size에 대한 정보만 가져옴
        List<Long> sizeIdList = dto.stream().map(e -> e.getSizeId()).collect(Collectors.toList());
        Map<Long, SizeEntity> sizeMap = iSizeRepository.findByIdIn(sizeIdList)
                .stream().collect(Collectors.toMap(SizeEntity::getId, Function.identity()));
        dto.forEach(e -> dtoMap.get(e)
                .changeMenuIdAndSizeId(e.getCartOrMyMenuId(), e.getSizeId())
                .changeMenuNameAndImgAndPrice(sizeMap.get(e.getSizeId())));
        // 옵션에 대한 정보만 가져옴

        return new ArrayList<>(dtoMap.values());
    }

    // 7-2. 장바구니 목록 조회 (requestparam)
    @Override
    public CartAndOptionResServDto getMenuCart(Long cartOrMyMenuId, Long sizeId, List<Long> optionIdList) {
        SizeEntity sizeEntity = iSizeRepository.findById(sizeId).get();
        CartAndOptionResServDto cartAndOptionResServDto = new CartAndOptionResServDto();
        return cartAndOptionResServDto.setFieldData(cartOrMyMenuId, sizeEntity);
    }

    // 8. 메뉴 하위 정보 상세 조회
    @Override
    public MenuDetailResDto getMenuDetail(Long menuId) {
        MenuEntity menuEntity = iMenuRepository.findById(menuId).get();
//        NutritionEntity nutritionEntity = nutritionRepository.findNutritionListByMenu(menuId);

        return new MenuDetailResDto(menuEntity);
    }

    // 9. 소 카테고리 - 메뉴 조회(관리자용)
    @Override
    public List<MenuForAdminDto> getMenuForAdmin(Long categorySId) {
        List<MenuEntity> menuByCategoryS = iMenuRepository.findMenuByCategoryS(categorySId);

        return menuByCategoryS.stream().map(e ->
                new MenuForAdminDto(e.getId(), e.getName(), e.getTemperatureEntityList().get(0).getMenuImg()))
                .collect(Collectors.toList());
    }

    // 10. 메뉴 검색 (이름)
    @Override
    public List<MenuListResDto> getMenuListSearchByName(String searchKeyword) {

        List<MenuEntity> menuEntityList = iMenuRepository.findByNameContaining(searchKeyword);

        return menuEntityList.stream().map(e ->
                new MenuListResDto(e.getId(), e.getTemperatureEntityList(), e.getPrice(), TEMPERATURE_POLICY))
                .collect(Collectors.toList());

    }
}