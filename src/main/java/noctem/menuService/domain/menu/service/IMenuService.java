package noctem.menuService.domain.menu.service;

import noctem.menuService.domain.categoryS.dto.CategorySDto;
import noctem.menuService.domain.menu.dto.MenuDto;

public interface IMenuService {

    /*
        1. 메뉴 등록
        2. 메뉴 수정
        3. 메뉴 삭제
        4. 메뉴 전체 조회
        5. 메뉴 단건 조회
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
}
