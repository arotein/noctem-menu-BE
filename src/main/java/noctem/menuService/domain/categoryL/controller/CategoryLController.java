package noctem.menuService.domain.categoryL.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.categoryL.dto.CategoryLDto;
import noctem.menuService.domain.categoryL.dto.CategoryLResDto;
import noctem.menuService.domain.categoryL.dto.vo.RequestCategoryL;
import noctem.menuService.domain.categoryL.dto.vo.ResponseCategoryL;
import noctem.menuService.domain.categoryL.service.ICategoryLService;
import noctem.menuService.global.common.CommonResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu-service")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryLController {

    private final ICategoryLService iCategoryLService;

    /*
        1. 대 카테고리 등록
        2. 대 카테고리 수정
        3. 대 카테고리 전체 조회
        4. 대 카테고리 단건 조회
     */

    // 1. 대 카테고리 등록
    @PostMapping("/categoryL")
    public ResponseEntity<ResponseCategoryL> addCategoryL(@RequestBody RequestCategoryL requestCategoryL){

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategoryLDto categoryLDto = mapper.map(requestCategoryL, CategoryLDto.class); // RequestCategoryL -> CategoryLDto
        iCategoryLService.addCategoryL(categoryLDto);

        ResponseCategoryL responseCategoryL = mapper.map(categoryLDto, ResponseCategoryL.class); // CategoryLDto -> ResponseCategoryL

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCategoryL);
    }

    // 2. 대 카테고리 수정
    @PutMapping("/categoryL/{categoryLId}")
    public ResponseEntity<ResponseCategoryL> editCategoryL(@PathVariable Long categoryLId,
                                                           @RequestBody RequestCategoryL requestCategoryL) throws Exception {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategoryLDto categoryLDto = mapper.map(requestCategoryL, CategoryLDto.class); // RequestCategoryL -> CategoryLDto
        iCategoryLService.editCategoryL(categoryLId, categoryLDto);

        ResponseCategoryL responseCategoryL = mapper.map(categoryLDto, ResponseCategoryL.class); // CategoryLDto -> ResponseCategoryL

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoryL);
    }

    // 3. 대 카테고리 전체 조회
    @GetMapping("/categoryL")
    public CommonResponse getAllCategoryL(){
        return CommonResponse.builder()
                .data(iCategoryLService.getAllCategoryL())
                .build();
    }

    // 4. 대 카테고리 - 소 카테고리 리스트 조회

    // 5. 대 카테고리 단건 조회
    @GetMapping("/categoryL/{categoryLId}")
    public ResponseEntity<ResponseCategoryL> getOneCategoryL(@PathVariable Long categoryLId) throws Exception {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategoryLDto categoryLDto = iCategoryLService.getOneCategoryL(categoryLId);
        ResponseCategoryL responseCategoryL = mapper.map(categoryLDto, ResponseCategoryL.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoryL);
    }
}
