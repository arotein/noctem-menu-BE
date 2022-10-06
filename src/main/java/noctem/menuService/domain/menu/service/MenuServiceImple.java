package noctem.menuService.domain.menu.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.menu.dto.CartAndOptionResServDto;
import noctem.menuService.domain.menu.dto.CartAndOptionsReqServDto;
import noctem.menuService.domain.menu.dto.MenuDto;
import noctem.menuService.domain.menu.dto.MenuListResDto;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.menu.repository.IMenuRepository;
import noctem.menuService.domain.personalOption.entity.PersonalOptionEntity;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.size.repository.ISizeRepository;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImple implements IMenuService {

    private final IMenuRepository iMenuRepository;
    private final ISizeRepository iSizeRepository;
    private final String TEMPERATURE_POLICY = "ice";

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
        6. 소카테고리-메뉴 조회
        7. 장바구니 목록 조회
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
        List<MenuEntity> menuList = iMenuRepository.findMenuByCategoryS(categorySId);
        return menuList.stream().map(e ->
                        new MenuListResDto(e.getId(), e.getTemperatureEntityList(), e.getPrice(), TEMPERATURE_POLICY))
                .collect(Collectors.toList());
    }

    // 7. 장바구니 목록 조회
    @Override
    public List<CartAndOptionResServDto> getMenuCart(List<CartAndOptionsReqServDto> cartAndOptionsReqServDto) {

//        SizeEntity sizeEntity = iSizeRepository.findById(cartAndOptionsReqServDto.getSizeId()).get();
//        TemperatureEntity temperatureEntity = sizeEntity.getTemperatureEntity();
//        MenuEntity menuEntity = temperatureEntity.getMenuEntity();
//        List<PersonalOptionEntity> personalOptionEntityList = menuEntity.getPersonalOptionEntityList();

        List<Long> sizeIdList = cartAndOptionsReqServDto.stream().map(e -> e.getSizeId()).collect(Collectors.toList());

        List<SizeEntity> bySizeIdIn = iSizeRepository.findByIdIn(sizeIdList);

        return bySizeIdIn.stream().map(e -> new CartAndOptionResServDto(e.getTemperatureEntity().getMenuName(),
                e.getTemperatureEntity().getMenuEngName(), e.getTemperatureEntity().getMenuImg(),
                e.getTemperatureEntity().getMenuEntity().getPrice() + e.getExtraCost()))
                .collect(Collectors.toList());
    }
}