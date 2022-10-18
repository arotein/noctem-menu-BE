package noctem.menuService.domain.categoryS.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.menuService.domain.categoryS.dto.CategorySDto;
import noctem.menuService.domain.categoryS.dto.CategorySResDto;
import noctem.menuService.domain.categoryS.dto.vo.RequestCategoryS;
import noctem.menuService.domain.categoryS.dto.vo.ResponseCategoryS;
import noctem.menuService.domain.categoryS.service.ICategorySService;
import noctem.menuService.domain.menu.dto.MenuListResDto;
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
@Slf4j
public class CategorySController {

    private final ICategorySService iCategorySService;

    /*
        1. 소 카테고리 등록
        2. 소 카테고리 수정
        3. 소 카테고리 삭제
        4. 소 카테고리 전체 조회
        5. 소 카테고리 단건 조회
        6. 대 카테고리 - 소 카테고리 리스트 조회
     */

    // 1. 소 카테고리 등록
    @PostMapping("/categoryS")
    public ResponseEntity<ResponseCategoryS> addCategoryS(@RequestBody RequestCategoryS requestCategoryS){

        ModelMapper mapperMapper = new ModelMapper(); // 모델매퍼 객체 사용

        mapperMapper.typeMap(ResponseCategoryS.class, CategorySDto.class).addMappings(mapper -> {
            mapper.map(ResponseCategoryS::getCategorySName, CategorySDto::setCategorySName);
        });

        System.out.println("requestCategoryS = " + requestCategoryS);

        CategorySDto categorySDto = mapperMapper.map(requestCategoryS, CategorySDto.class); // RequestCategoryS -> CategorySDto

        System.out.println("categorySDto = " + categorySDto);

        iCategorySService.addCategoryS(categorySDto);

        ResponseCategoryS responseCategoryS = mapperMapper.map(categorySDto, ResponseCategoryS.class); // CategorySDto -> ResponseCategoryS

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCategoryS);
    }

    // 2. 소 카테고리 수정
    @PutMapping("/categoryS/{categorySId}")
    public ResponseEntity<ResponseCategoryS> editCategoryS(@PathVariable Long categorySId,
                                                           @RequestBody RequestCategoryS requestCategoryS) throws Exception {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategorySDto categorySDto = mapper.map(requestCategoryS, CategorySDto.class); // RequestCategoryS -> CategorySDto
        iCategorySService.editCategoryS(categorySId, categorySDto);

        ResponseCategoryS responseCategoryS = mapper.map(categorySDto, ResponseCategoryS.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoryS);
    }

    // 3. 소 카테고리 삭제
    @DeleteMapping("/categoryS/{categorySId}")
    public ResponseEntity<ResponseCategoryS> deleteCategoryS(@PathVariable Long categorySId) throws Exception {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategorySDto categorySDto = iCategorySService.deleteCategoryS(categorySId);

        ResponseCategoryS responseCategoryS = mapper.map(categorySDto, ResponseCategoryS.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoryS);
    }

    // 4. 소 카테고리 전체 조회
    @GetMapping("/categoryS")
    public ResponseEntity<List<ResponseCategoryS>> getAllCategoryS(){

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        Iterable<CategorySDto> categorySDtoIterable = iCategorySService.getAllCategoryS();
        List<ResponseCategoryS> responseCategorySList = new ArrayList<>();

        categorySDtoIterable.forEach(categorySDto -> {
            responseCategorySList.add(mapper.map(categorySDto, ResponseCategoryS.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseCategorySList);
    }

    // 5. 소 카테고리 단건 조회
    @GetMapping("/categoryS/{categorySId}")
    public ResponseEntity<ResponseCategoryS> getOneCategoryS(@PathVariable Long categorySId) throws Exception {

        ModelMapper mapper = new ModelMapper(); // 모델매퍼 객체 사용
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 모델매퍼 매핑전략 (정확히 일치하도록)

        CategorySDto categorySDto = iCategorySService.getOneCategoryS(categorySId);
        ResponseCategoryS responseCategoryS = mapper.map(categorySDto, ResponseCategoryS.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseCategoryS);
    }

    // 6. 대 카테고리 - 소 카테고리 리스트 조회
    @GetMapping("/{categoryLId}/categoryS")
    public CommonResponse getCategorySListByCategoryL(@PathVariable Long categoryLId){

        List<CategorySResDto> categorySResDtoList = iCategorySService.getCategorySListByCategoryL(categoryLId);
        categorySResDtoList.forEach(e -> e.setIndex(categorySResDtoList.indexOf(e)));

        return CommonResponse.builder()
                .data(categorySResDtoList)
                .build();
    }

    // 7. 온도 - 소 카테고리 리스트 조회(관리자용)
    @GetMapping("/admin/categoryS/{temperature}")
    public CommonResponse getCategorySListByTemperature(@PathVariable String temperature){

        List<CategorySResDto> categorySListByTemperature = iCategorySService.getCategorySListByTemperature(temperature);
        categorySListByTemperature.forEach(e -> e.setIndex(categorySListByTemperature.indexOf(e)));

        return CommonResponse.builder()
                .data(categorySListByTemperature)
                .build();
    }
}
