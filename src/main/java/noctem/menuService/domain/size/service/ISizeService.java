package noctem.menuService.domain.size.service;

import noctem.menuService.domain.size.dto.SizeDto;
import noctem.menuService.domain.temperature.dto.TemperatureDto;

public interface ISizeService {

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
     */

    // 1. 온도 등록
    SizeDto addSize(SizeDto sizeDto);

    // 2. 온도 수정
    SizeDto editSize(Long sizeId, SizeDto sizeDto);

    // 3. 온도 삭제
    SizeDto deleteSize(Long sizeId);

    // 4. 온도 전체 조회
    Iterable<SizeDto> getAllSize();

    // 5. 온도 단건 조회
    SizeDto getOneSize(Long sizeId);

}
