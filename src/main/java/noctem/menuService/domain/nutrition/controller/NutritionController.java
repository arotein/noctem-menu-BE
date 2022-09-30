package noctem.menuService.domain.nutrition.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.nutrition.dto.NutritionResDto;
import noctem.menuService.domain.nutrition.service.INutritionService;
import noctem.menuService.global.common.CommonResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu-service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class NutritionController {

    private final INutritionService iNutritionService;

    /*
        1. 영양정보 등록
        2. 영양정보 수정
        3. 영양정보 삭제
        4. 영양정보 전체 조회
        5. 영양정보 단건 조회
     */

    // 4. 영양정보 전체 조회
    @GetMapping("/nutrition")
    public CommonResponse getAllNutrition(){
        log.info("getAllNutrition 실행");
        return CommonResponse.builder()
                .data(iNutritionService.getAllNutrition())
                .build();
    }

    // 5. 영양정보 단건 조회
    @GetMapping("/nutrition/{nutritionId}")
    public CommonResponse getOneNutrition(@PathVariable Long nutritionId){
        return CommonResponse.builder()
                .data(iNutritionService.getOneNutrition(nutritionId))
                .build();
    }

}
