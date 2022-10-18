package noctem.menuService.domain.temperature.service;

import noctem.menuService.domain.temperature.dto.TemperatureDto;
import noctem.menuService.domain.temperature.dto.TemperatureListForAdminResDto;
import noctem.menuService.domain.temperature.dto.TemperatureListResDto;

import java.util.List;

public interface ITemperatureService {

    /*
        1. 온도 등록
        2. 온도 수정
        3. 온도 삭제
        4. 온도 전체 조회
        5. 온도 단건 조회
        6. 메뉴-온도 리스트 조회
        7. 소 카테고리 - 온도 조회(관리자용)
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

    // 6. 메뉴-온도 리스트 조회
    List<TemperatureListResDto> getTemperatureByMenu(Long menuId);

    // 7. 소 카테고리 - 온도 조회(관리자용)
    List<TemperatureListForAdminResDto> getTemperatureForAdmin(Long categorySId, String temperature);
}
