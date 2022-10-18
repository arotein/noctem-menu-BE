package noctem.menuService.domain.categoryS.service;

import noctem.menuService.domain.categoryS.dto.CategorySDto;
import noctem.menuService.domain.categoryS.dto.CategorySResDto;

import java.util.List;

public interface ICategorySService {

    /*
        1. 소 카테고리 등록
        2. 소 카테고리 수정
        3. 소 카테고리 삭제
        4. 소 카테고리 전체 조회
        5. 소 카테고리 단건 조회
        6. 대 카테고리 - 소 카테고리 리스트 조회
        7. 온도 - 소 카테고리 리스트 조회(관리자용)
     */

    // 1. 소 카테고리 등록
    CategorySDto addCategoryS(CategorySDto categorySDto);

    // 2. 소 카테고리 수정
    CategorySDto editCategoryS(Long categorySId, CategorySDto categorySDto) throws Exception;

    // 3. 소 카테고리 삭제
    CategorySDto deleteCategoryS(Long categorySId) throws Exception;

    // 4. 소 카테고리 전체 조회
    Iterable<CategorySDto> getAllCategoryS();

    // 5. 소 카테고리 단건 조회
    CategorySDto getOneCategoryS(Long categorySId) throws Exception;

    // 6. 대 카테고리 - 소 카테고리 리스트 조회
    List<CategorySResDto> getCategorySListByCategoryL(Long categoryLId);

    // 7. 온도 - 소 카테고리 리스트 조회(관리자용)
    List<CategorySResDto> getCategorySListByTemperature(String temperature);

}
