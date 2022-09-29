package noctem.menuService.domain.temperature.service;

import noctem.menuService.domain.temperature.dto.TemperatureDto;

public interface ITemperatureService {

    /*
        1. 온도 등록
        2. 온도 수정
        3. 온도 삭제
        4. 온도 전체 조회
        5. 온도 단건 조회
     */

    // 1. 온도 등록
    TemperatureDto addTemperature(TemperatureDto temperatureDto);

    // 2. 온도 수정
    TemperatureDto editTemperature(Long temperatureId, TemperatureDto temperatureDto) throws Exception;

    // 3. 온도 삭제
    TemperatureDto deleteTemperature(Long temperatureId) throws Exception;

    // 4. 온도 전체 조회
    Iterable<TemperatureDto> getAllTemperatrue();

    // 5. 온도 단건 조회
    TemperatureDto getOneTemperature(Long temperatureId) throws Exception;
}
