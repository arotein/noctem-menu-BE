package noctem.menuService.domain.categoryL.service;

import noctem.menuService.domain.categoryL.dto.CategoryLDto;
import noctem.menuService.domain.categoryL.dto.CategoryLResDto;

import java.util.List;

public interface ICategoryLService {

    /*
        1. 대 카테고리 등록
        2. 대 카테고리 수정
        3. 대 카테고리 전체 조회
        4. 대 카테고리 단건 조회
     */

    // 1. 대 카테고리 등록
    CategoryLDto addCategoryL(CategoryLDto categoryLDto);

    // 2. 대 카테고리 수정
    CategoryLDto editCategoryL(Long categoryLId, CategoryLDto categoryLDto) throws Exception;

    // 3. 대 카테고리 전체 조회
    List<CategoryLResDto> getAllCategoryL();

    // 4. 대 카테고리 단건 조회
    CategoryLDto getOneCategoryL(Long categoryLId) throws Exception;
}
