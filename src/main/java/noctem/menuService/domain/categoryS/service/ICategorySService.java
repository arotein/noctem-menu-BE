package noctem.menuService.domain.categoryS.service;

import noctem.menuService.domain.categoryS.dto.CategorySDto;

public interface ICategorySService {

    /*
        1. 소 카테고리 등록
        2. 소 카테고리 수정
        3. 소 카테고리 삭제
        4. 소 카테고리 전체 조회
        5. 소 카테고리 단건 조회
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
}
