package noctem.menuService.domain.temperature.service;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.menu.dto.MenuListResDto;
import noctem.menuService.domain.temperature.dto.TemperatureDto;
import noctem.menuService.domain.temperature.dto.TemperatureListResDto;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import noctem.menuService.domain.temperature.repository.ITemperatureRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImple implements ITemperatureService{

    private final ITemperatureRepository iTemperatureRepository;

    /*
        1. 온도 등록
        2. 온도 수정
        3. 온도 삭제
        4. 온도 전체 조회
        5. 온도 단건 조회
        6. 메뉴-온도 리스트 조회
     */

    // 1. 온도 등록
    @Override
    public TemperatureDto addTemperature(TemperatureDto temperatureDto) {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        TemperatureEntity temperatureEntity = mapper.map(temperatureDto, TemperatureEntity.class); // TemperatureDto -> TemperatureEntity
        iTemperatureRepository.save(temperatureEntity);

        TemperatureDto resultTemperatureDto = mapper.map(temperatureEntity, TemperatureDto.class); // TemperatureEntity -> TemperatureDto

        return resultTemperatureDto;
    }

    // 2. 온도 수정
    @Override
    public TemperatureDto editTemperature(Long temperatureId, TemperatureDto temperatureDto) throws Exception {

        Optional<TemperatureEntity> temperatureEntity = iTemperatureRepository.findById(temperatureId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(temperatureEntity.isPresent()){
            TemperatureEntity temperature = mapper.map(temperatureDto, TemperatureEntity.class); // TemperatureDto -> TemperatureEntity
            temperature.setId(temperatureId); // 동일 id로 수정
            iTemperatureRepository.save(temperature); // 엔터티 저장

            TemperatureDto resultTemperatureDto = mapper.map(temperature, TemperatureDto.class); // TemperatureEntity -> TemperatureDto

            return resultTemperatureDto;
        } else {
            throw new Exception("해당하는 온도 id가 없습니다.");
        }
    }

    // 3. 온도 삭제
    @Override
    public TemperatureDto deleteTemperature(Long temperatureId) throws Exception {

        Optional<TemperatureEntity> temperatureEntity = iTemperatureRepository.findById(temperatureId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if(temperatureEntity.isPresent()){
            temperatureEntity.get().setIsDeleted(true); // 삭제여부 true

            iTemperatureRepository.save(temperatureEntity.get());
            TemperatureDto temperatureDto = mapper.map(temperatureEntity, TemperatureDto.class);

            return temperatureDto;
        } else {
            throw new Exception("해당하는 온도 id가 없습니다.");
        }
    }

    // 4. 온도 전체 조회
    @Override
    public Iterable<TemperatureDto> getAllTemperatrue() {

        List<TemperatureEntity> temperatureEntityList = iTemperatureRepository.findAll();
        List<TemperatureDto> temperatureDtoList = new ArrayList<>(); // 비어 있는 temperatureDto 리스트 선언

        temperatureEntityList.forEach(temperatureEntity -> {
            if (temperatureEntity.getIsDeleted() == false)
                temperatureDtoList.add(new ModelMapper().map(temperatureEntity, TemperatureDto.class));
        });

        return temperatureDtoList;
    }

    // 5. 온도 단건 조회
    @Override
    public TemperatureDto getOneTemperature(Long temperatureId) throws Exception {

        Optional<TemperatureEntity> temperatureEntity = iTemperatureRepository.findById(temperatureId);

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        if (temperatureEntity.isPresent() && temperatureEntity.get().getIsDeleted() == false) { // 논리삭제가 false인 것
            TemperatureDto temperatureDto = mapper.map(temperatureEntity, TemperatureDto.class); // TemperatrueEntity -> TemperatureDto
            return temperatureDto;
        } else {
            throw new Exception("해당하는 온도 id가 없습니다.");
        }
    }

    // 6. 메뉴-온도 리스트 조회
    @Override
    public List<TemperatureListResDto> getTemperatureByMenu(Long menuId) {

        List<TemperatureEntity> temperatureEntityList = iTemperatureRepository.findTempByMenu(menuId);

//        return menuList.stream().map(e ->
//                        new MenuListResDto(e.getTemperatureEntityList(), e.getPrice(), TEMPERATURE_POLICY))
//                .collect(Collectors.toList());

        return temperatureEntityList.stream().map(e ->
                new TemperatureListResDto(e.getTemperature()))
                .collect(Collectors.toList());
    }
}
