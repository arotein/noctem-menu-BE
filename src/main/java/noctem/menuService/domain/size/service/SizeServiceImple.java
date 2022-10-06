package noctem.menuService.domain.size.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.personalOption.entity.PersonalOptionEntity;
import noctem.menuService.domain.size.dto.PersonalOptionListResDto;
import noctem.menuService.domain.size.dto.SizeByTempResDto;
import noctem.menuService.domain.size.dto.SizeReqDto;
import noctem.menuService.domain.size.dto.SizeResDto;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.size.repository.ISizeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeServiceImple implements ISizeService{

    private final ISizeRepository iSizeRepository;

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
        6. 온도-사이즈 리스트 조회
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

        if(sizeEntity.isPresent()){
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

        if(sizeEntity.isPresent()){
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
            new SizeByTempResDto(sizeEntity.getId(), sizeEntity.getSize(), sizeEntity.getExtraCost()))
                .collect(Collectors.toList());
    }

    // 7. 사이즈-퍼스널옵션 리스트 조회
    @Override
    public List<PersonalOptionListResDto> getSizeListByTemperature2(Long sizeId) {

        SizeEntity sizeEntity = iSizeRepository.findById(sizeId).get();

        MenuEntity menuEntity = sizeEntity.getTemperatureEntity().getMenuEntity();

        List<PersonalOptionEntity> personalOptionEntityList = menuEntity.getPersonalOptionEntityList();

        return personalOptionEntityList.stream().map(e ->
                new PersonalOptionListResDto(e.getId(), e.getMenuEntity().getId(), e.getOptionType(), e.getOptionNumber()
                , e.getOptionName(), e.getAmount(), e.getExtraCost(), e.getIsEssential(), e.getIsDefault()))
                .collect(Collectors.toList());
    }
}