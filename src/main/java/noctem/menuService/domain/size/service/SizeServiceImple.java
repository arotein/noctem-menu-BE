package noctem.menuService.domain.size.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.size.dto.*;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.size.repository.ISizeRepository;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import noctem.menuService.global.common.CommonException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SizeServiceImple implements ISizeService {

    private final ISizeRepository iSizeRepository;

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
        6. 온도-사이즈 리스트 조회
        7. 사이즈-메뉴 조회(BE)
        8. 사이즈ID-메뉴 조회(프론트-장바구니 조회용)
        9. 사이즈ID-메뉴 조회(프론트-나만의메뉴 조회용)
        10. 사이즈ID-메뉴 조회(결제 조회용)
        11. 사이즈ID-메뉴 조회(베트스 메뉴 조회용)
     */

    // 1. 사이즈 등록
    @Override
    public SizeReqDto addSize(SizeReqDto sizeReqDto) {
        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        SizeEntity sizeEntity = mapper.map(sizeReqDto, SizeEntity.class); // SizeDto -> SizeEntity
        iSizeRepository.save(sizeEntity);

        SizeReqDto resultSizeReqDto = mapper.map(sizeEntity, SizeReqDto.class); // SizeEntity -> SizeDto

        return resultSizeReqDto;
    }

    // 2. 사이즈 수정
    @Override
    public SizeReqDto editSize(Long sizeId, SizeReqDto sizeReqDto) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (sizeEntity.isPresent()) {
            SizeEntity size = mapper.map(sizeReqDto, SizeEntity.class); // SizeDto -> SizeEntity
            size.setId(sizeId); // 동일 id로 수정
            iSizeRepository.save(size); // 엔터티 저장

            SizeReqDto resultSizeReqDto = mapper.map(size, SizeReqDto.class); // SizeEntity -> SizeDto

            return resultSizeReqDto;
        }
        return null;
    }

    // 3. 사이즈 삭제
    @Override
    public SizeResDto deleteSize(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (sizeEntity.isPresent()) {
            sizeEntity.get().setIsDeleted(true); // 삭제여부 true

            iSizeRepository.save(sizeEntity.get());
            SizeResDto sizeDto = mapper.map(sizeEntity, SizeResDto.class);

            return sizeDto;
        }
        return null;
    }

    // 4. 사이즈 전체 조회
    @Override
    public Iterable<SizeResDto> getAllSize() {
        List<SizeEntity> sizeEntityList = iSizeRepository.findAll();
        List<SizeResDto> sizeDtoList = new ArrayList<>(); // 비어 있는 sizeDto 리스트 선언

        sizeEntityList.forEach(sizeEntity -> {
            if (!sizeEntity.getIsDeleted())
                sizeDtoList.add(new ModelMapper().map(sizeEntity, SizeResDto.class));
        });

        return sizeDtoList;
    }

    // 5. 사이즈 단건 조회
    @Override
    public SizeResDto getOneSize(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (sizeEntity.isPresent() && !sizeEntity.get().getIsDeleted()) { // 논리삭제가 false인 것
            SizeResDto sizeDto = mapper.map(sizeEntity, SizeResDto.class); // SizeEntity -> SizeDto
            return sizeDto;
        }
        return null;
    }

    // 6. 온도-사이즈 리스트 조회
    @Override
    public List<SizeByTempResDto> getSizeListByTemperature(Long temperatureId) {

        List<SizeEntity> sizeByTemp = iSizeRepository.findSizeListByTemp(temperatureId);

        return sizeByTemp.stream().map(sizeEntity ->
                        new SizeByTempResDto(sizeEntity.getId(), sizeEntity.getSize(), sizeEntity.getExtraCost(),
                                sizeEntity.getCapacity()))
                .collect(Collectors.toList());
    }

    // 7. 사이즈-메뉴 조회(BE)
    @Override
    public SizeMenuResDto getSizeMenu(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);
        TemperatureEntity temperatureEntity = sizeEntity.get().getTemperatureEntity();
        MenuEntity menuEntity = temperatureEntity.getMenuEntity();

        if (sizeEntity.isPresent()) {
            return new SizeMenuResDto(sizeEntity.get().getId(), menuEntity.getId(), temperatureEntity.getMenuName(),
                    menuEntity.getName(), menuEntity.getPrice() + sizeEntity.get().getExtraCost());
        }
        return null;
    }

    // 8. 사이즈ID-메뉴 조회(프론트-장바구니 조회용)
    @Override
    public MenuBySizeForCartDto getMenuBySizeForCart(Long sizeId, Long cartId) {

        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);
        TemperatureEntity temperatureEntity = sizeEntity.get().getTemperatureEntity();
        MenuEntity menuEntity = temperatureEntity.getMenuEntity();

        if (sizeEntity.isPresent()) {
            return new MenuBySizeForCartDto(cartId, menuEntity.getId(), temperatureEntity.getMenuName(), temperatureEntity.getMenuEngName(),
                    temperatureEntity.getMenuImg(), temperatureEntity.getTemperature(),
                    menuEntity.getPrice() + sizeEntity.get().getExtraCost(), sizeEntity.get().getSize());
        }

        return null;
    }

    // 9. 사이즈ID-메뉴 조회(프론트-나만의메뉴 조회용)
    @Override
    public MenuBySizeForMyMenuDto getMenuBySizeForMyMenu(Long sizeId, Long myMenuId) {

        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);
        TemperatureEntity temperatureEntity = sizeEntity.get().getTemperatureEntity();
        MenuEntity menuEntity = temperatureEntity.getMenuEntity();

        if (sizeEntity.isPresent()) {
            return new MenuBySizeForMyMenuDto(myMenuId, temperatureEntity.getMenuName(), temperatureEntity.getMenuImg(),
                    temperatureEntity.getTemperature(), menuEntity.getPrice() + sizeEntity.get().getExtraCost(),
                    sizeEntity.get().getSize());
        }

        return null;
    }

    // 10. 사이즈ID-메뉴 조회(결제 조회용)
    @Override
    public MenuBySizeForPurchaseDto getMenuBySizeForPurchase(Long sizeId, Long cartId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);
        if (!sizeEntity.isPresent()) {
            throw CommonException.builder().errorCode(3005).httpStatus(HttpStatus.BAD_REQUEST).build();
        }

        TemperatureEntity temperatureEntity = sizeEntity.get().getTemperatureEntity();
        MenuEntity menuEntity = temperatureEntity.getMenuEntity();

        Long categorySId = temperatureEntity.getMenuEntity().getCategorySEntity().getId();

        String shortenTemp = "";
        String shortenSize = "";

        switch (temperatureEntity.getTemperature()){
            case "ice":
                shortenTemp = "I";
                break;
            case "hot":
                shortenTemp = "H";
        }

        switch (sizeEntity.get().getSize()){
            case "Tall":
                shortenSize = "T";
                break;
            case "Grande":
                shortenSize = "G";
                break;
            case "Venti":
                shortenSize = "V";
        }

        System.out.println("shortenSize = " + shortenSize);
        System.out.println("shortenTemp = " + shortenTemp);

        MenuBySizeForPurchaseDto result = new MenuBySizeForPurchaseDto(cartId, menuEntity.getCategorySEntity().getCategorySName()
                ,menuEntity.getName(),
                shortenTemp + "-" + shortenSize + ")" + temperatureEntity.getMenuEntity().getShortenName(),
                menuEntity.getPrice() + sizeEntity.get().getExtraCost(),
                sizeEntity.get().getTemperatureEntity().getMenuImg());

        if (temperatureEntity.getTemperature().equals("ice") &&
                (!(categorySId == 3L) && !(categorySId == 4L) && !(categorySId == 8L) && !(categorySId == 9L) &&
                !(categorySId == 10L))) {
            result.setMenuFullName("아이스 " + menuEntity.getName());
        }
        return result;
    }

    // 11. 사이즈ID-메뉴 조회(베트스 메뉴 조회용)

    @Override
    public MenuBySizeForBestDto getMenuBySizeForBest(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);
        TemperatureEntity temperatureEntity = sizeEntity.get().getTemperatureEntity();
        MenuEntity menuEntity = temperatureEntity.getMenuEntity();

        if (sizeEntity.isPresent()) {
            return new MenuBySizeForBestDto(menuEntity.getId(), temperatureEntity.getMenuName(),
                    temperatureEntity.getMenuEngName(), temperatureEntity.getMenuImg(), temperatureEntity.getTemperature(),
                    menuEntity.getPrice(), sizeEntity.get().getSize());
        }

        return null;
    }
}