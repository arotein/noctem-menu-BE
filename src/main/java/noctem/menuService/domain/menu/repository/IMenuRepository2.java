package noctem.menuService.domain.menu.repository;

import noctem.menuService.domain.menu.entity.MenuEntity;

import java.util.List;

public interface IMenuRepository2 {

    List<MenuEntity> findMenuByCategoryS(Long categorySId);

}
