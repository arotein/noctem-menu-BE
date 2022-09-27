package noctem.menuService.domain.menu.repository;

import noctem.menuService.domain.menu.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRepository extends JpaRepository<MenuEntity, Long> {
}
