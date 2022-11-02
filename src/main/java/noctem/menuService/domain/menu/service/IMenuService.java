package noctem.menuService.domain.menu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import noctem.menuService.domain.menu.dto.*;

import java.awt.*;
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
        8. 메뉴 하위 정보 상세 조회
        9. 소 카테고리 - 메뉴 조회(관리자용)
        10. 메뉴 검색 (이름)
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
    List<MenuListResDto> getMenuList(Long categorySId) throws JsonProcessingException;

    // 7-1. 장바구니 목록 조회 (리스트)
    List<CartAndOptionListResServDto> getMenuCartList(List<CartAndOptionsReqServDto> cartAndOptionsReqServDto);

    // 7-2. 장바구니 목록 조회 (requestparam)
    CartAndOptionResServDto getMenuCart(Long cartOrMyMenuId, Long sizeId, List<Long> optionIdList);

    // 8. 메뉴 하위 정보 상세 조회
    MenuDetailResDto getMenuDetail(Long menuId);

    // 9. 소 카테고리 - 메뉴 조회(관리자용)
    List<MenuForAdminDto> getMenuForAdmin(Long categorySId);

    // 10. 메뉴 검색 (이름)
    List<MenuListResDto> getMenuListSearchByName(String searchKeyword);
}
