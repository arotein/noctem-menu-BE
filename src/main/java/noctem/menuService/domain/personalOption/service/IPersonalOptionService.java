package noctem.menuService.domain.personalOption.service;

import noctem.menuService.domain.personalOption.dto.PersonalOptionListResDto;
import noctem.menuService.domain.personalOption.dto.PersonalOptionReqDto;
import noctem.menuService.domain.personalOption.dto.PersonalOptionResDto;

import java.util.List;

public interface IPersonalOptionService {

    /*
        1. 퍼스널 옵션 등록
        2. 퍼스널 옵션 수정
        3. 퍼스널 옵션 삭제
        4. 퍼스널 옵션 전체 조회
        5. 퍼스널 옵션 단건 조회
        6. 메뉴-퍼스널 옵션 리스트 조회
     */

    // 1. 퍼스널 옵션 등록
    PersonalOptionReqDto addPersonalOption(PersonalOptionReqDto personalOptionReqDto);

    // 2. 퍼스널 옵션 수정
    PersonalOptionReqDto editPersonalOption(Long personalOptionId, PersonalOptionReqDto personalOptionReqDto);

    // 3. 퍼스널 옵션 삭제
    PersonalOptionResDto deletePersonalOption(Long personalOptionId, PersonalOptionResDto personalOptionResDto);

    // 4. 퍼스널 옵션 전체 조회
    Iterable<PersonalOptionResDto> getAllPersonalOption(PersonalOptionResDto personalOptionResDto);

    // 5. 퍼스널 옵션 단건 조회
    PersonalOptionResDto getOnePersonalOption(Long personalOptionId);

    // 6. 메뉴-퍼스널 옵션 리스트 조회
    List<PersonalOptionListResDto> getPersonalOptionListByMenu(Long menuId);
}
