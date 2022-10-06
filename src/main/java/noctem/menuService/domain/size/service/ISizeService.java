package noctem.menuService.domain.size.service;

import noctem.menuService.domain.size.dto.PersonalOptionListResDto;
import noctem.menuService.domain.size.dto.SizeByTempResDto;
import noctem.menuService.domain.size.dto.SizeReqDto;
import noctem.menuService.domain.size.dto.SizeResDto;

import java.util.List;

public interface ISizeService {

    /*
        1. 사이즈 등록
        2. 사이즈 수정
        3. 사이즈 삭제
        4. 사이즈 전체 조회
        5. 사이즈 단건 조회
        6. 온도-사이즈 리스트 조회
     */

    // 1. 온도 등록
    SizeReqDto addSize(SizeReqDto sizeReqDto);

    // 2. 온도 수정
    SizeReqDto editSize(Long sizeId, SizeReqDto sizeReqDto);

    // 3. 온도 삭제
    SizeResDto deleteSize(Long sizeId);

    // 4. 온도 전체 조회
    Iterable<SizeResDto> getAllSize();

    // 5. 온도 단건 조회
    SizeResDto getOneSize(Long sizeId);

    // 6. 온도-사이즈 리스트 조회
    List<SizeByTempResDto> getSizeListByTemperature(Long temperatureId);


    List<PersonalOptionListResDto> getSizeListByTemperature2(Long sizeId);
}
