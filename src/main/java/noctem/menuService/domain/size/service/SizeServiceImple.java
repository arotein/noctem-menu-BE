package noctem.menuService.domain.size.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.size.dto.SizeDto;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.size.repository.ISizeRepository;
import noctem.menuService.domain.temperature.dto.TemperatureDto;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     */

    // 1. 사이즈 등록
    @Override
    public SizeDto addSize(SizeDto sizeDto) {
        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        SizeEntity sizeEntity = mapper.map(sizeDto, SizeEntity.class); // SizeDto -> SizeEntity
        iSizeRepository.save(sizeEntity);

        SizeDto resultSizeDto = mapper.map(sizeEntity, SizeDto.class); // SizeEntity -> SizeDto

        return resultSizeDto;
    }

    // 2. 사이즈 수정
    @Override
    public SizeDto editSize(Long sizeId, SizeDto sizeDto) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(sizeEntity.isPresent()){
            SizeEntity size = mapper.map(sizeDto, SizeEntity.class); // SizeDto -> SizeEntity
            size.setId(sizeId); // 동일 id로 수정
            iSizeRepository.save(size); // 엔터티 저장

            SizeDto resultSizeDto = mapper.map(size, SizeDto.class); // SizeEntity -> SizeDto

            return resultSizeDto;
        }
        return null;
    }

    // 3. 사이즈 삭제
    @Override
    public SizeDto deleteSize(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(sizeEntity.isPresent()){
            sizeEntity.get().setIsDeleted(true); // 삭제여부 true

            iSizeRepository.save(sizeEntity.get());
            SizeDto sizeDto = mapper.map(sizeEntity, SizeDto.class);

            return sizeDto;
        }
        return null;
    }

    // 4. 사이즈 전체 조회
    @Override
    public Iterable<SizeDto> getAllSize() {
        List<SizeEntity> sizeEntityList = iSizeRepository.findAll();
        List<SizeDto> sizeDtoList = new ArrayList<>(); // 비어 있는 sizeDto 리스트 선언

        sizeEntityList.forEach(sizeEntity -> {
            if (sizeEntity.getIsDeleted() == false)
                sizeDtoList.add(new ModelMapper().map(sizeEntity, SizeDto.class));
        });

        return sizeDtoList;
    }

    // 5. 사이즈 단건 조회
    @Override
    public SizeDto getOneSize(Long sizeId) {
        Optional<SizeEntity> sizeEntity = iSizeRepository.findById(sizeId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (sizeEntity.isPresent() && sizeEntity.get().getIsDeleted() == false) { // 논리삭제가 false인 것
            SizeDto sizeDto = mapper.map(sizeEntity, SizeDto.class); // SizeEntity -> SizeDto
            return sizeDto;
        }
        return null;
    }
}
