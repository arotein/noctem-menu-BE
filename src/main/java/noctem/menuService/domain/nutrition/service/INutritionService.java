package noctem.menuService.domain.nutrition.service;

import noctem.menuService.domain.nutrition.dto.NutritionResDto;

import java.util.List;

public interface INutritionService {

    /*
        1. 영양정보 등록
        2. 영양정보 수정
        3. 영양정보 삭제
        4. 영양정보 전체 조회
        5. 영양정보 단건 조회
        6. 메뉴 ID - 영양정보 조회
     */

    // 4. 영양정보 전체 조회
    List<NutritionResDto> getAllNutrition();

    // 5. 영양정보 전체 조회
    NutritionResDto getOneNutrition(Long nutritionId);
}
