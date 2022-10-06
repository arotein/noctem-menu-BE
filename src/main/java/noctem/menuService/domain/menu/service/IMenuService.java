package noctem.menuService.domain.menu.service;

import noctem.menuService.domain.menu.dto.*;

import java.util.List;

public interface IMenuService {

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
        6. 소카테고리-메뉴 조회
        7-1. 장바구니 목록 조회 (리스트)
        7-2. 장바구니 목록 조회 (requestparam)
     */

    // 1. 메뉴 등록
    MenuDto addMenu(MenuDto menuDto);

    // 2. 메뉴 수정
    MenuDto editMenu(Long menuId, MenuDto menuDto) throws Exception;

    // 3. 메뉴 삭제
    MenuDto deleteMenu(Long menuId) throws Exception;

    // 4. 메뉴 전체 조회
    Iterable<MenuDto> getAllMenu();

    // 5. 메뉴 단건 조회
    MenuDto getOneMenu(Long menuId) throws Exception;

    // 6. 소카테고리-메뉴 조회
    List<MenuListResDto> getMenuList(Long categorySId);

    // 7-1. 장바구니 목록 조회 (리스트)
    List<CartAndOptionListResServDto> getMenuCartList(List<CartAndOptionsReqServDto> cartAndOptionsReqServDto);

    // 7-2. 장바구니 목록 조회 (requestparam)
    CartAndOptionResServDto getMenuCart(Long cartOrMyMenuId, Long sizeId, List<Long> optionIdList);

}
