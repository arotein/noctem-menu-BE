package noctem.menuService.domain.temperature.controller;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.temperature.dto.TemperatureDto;
import noctem.menuService.domain.temperature.dto.vo.RequestTemperature;
import noctem.menuService.domain.temperature.dto.vo.ResponseTemperature;
import noctem.menuService.domain.temperature.service.ITemperatureService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu-service/temperature")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemperatureController {

    private final ITemperatureService iTemperatureService;

    /*
        1. 온도 등록
        2. 온도 수정
        3. 온도 삭제
        4. 온도 전체 조회
        5. 온도 단건 조회
     */

    // 1. 온도 등록
    @PostMapping
    public ResponseEntity<ResponseTemperature> addTemperature(@RequestBody RequestTemperature requestTemperature){

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        TemperatureDto temperatureDto = modelMapper.map(requestTemperature, TemperatureDto.class); // RequestTemperature -> TemperatureDto
        iTemperatureService.addTemperature(temperatureDto);

        ResponseTemperature responseTemperature = modelMapper.map(temperatureDto, ResponseTemperature.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseTemperature);
    }

    // 2. 온도 수정
    @PutMapping("/{temperatureId}")
    private ResponseEntity<ResponseTemperature> editTemperatrue(@PathVariable Long temperatureId,
                                                                @RequestBody RequestTemperature requestTemperature) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        TemperatureDto temperatureDto = modelMapper.map(requestTemperature, TemperatureDto.class); // RequestTemperature -> TemperatureDto
        iTemperatureService.editTemperature(temperatureId, temperatureDto);

        ResponseTemperature responseTemperature = modelMapper.map(temperatureDto, ResponseTemperature.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseTemperature);
    }

    // 3. 온도 삭제
    @DeleteMapping("/{temperatureId}")
    public ResponseEntity<ResponseTemperature> deleteTemperature(@PathVariable Long temperatureId) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        TemperatureDto temperatureDto = iTemperatureService.deleteTemperature(temperatureId);

        ResponseTemperature responseTemperature = modelMapper.map(temperatureDto, ResponseTemperature.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseTemperature);
    }

    // 4. 온도 전체 조회
    @GetMapping
    public ResponseEntity<List<ResponseTemperature>> getAllTemperature(){

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        Iterable<TemperatureDto> temperatureDtoIterable = iTemperatureService.getAllTemperatrue();
        List<ResponseTemperature> responseTemperatureList = new ArrayList<>();

        temperatureDtoIterable.forEach(temperatureDto -> {
            responseTemperatureList.add(modelMapper.map(temperatureDto, ResponseTemperature.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseTemperatureList);
    }

    // 5. 온도 단건 조회
    @GetMapping("/{temperatureId}")
    public ResponseEntity<ResponseTemperature> getOneTemperatrue(@PathVariable Long temperatureId) throws Exception {

        ModelMapper modelMapper = new ModelMapper(); // 모델매퍼 객체 사용

        TemperatureDto temperatureDto = iTemperatureService.getOneTemperature(temperatureId);

        ResponseTemperature responseTemperature = modelMapper.map(temperatureDto, ResponseTemperature.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseTemperature);
    }
}
