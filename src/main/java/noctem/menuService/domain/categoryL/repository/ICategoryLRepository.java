package noctem.menuService.domain.categoryL.repository;

import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryLRepository extends JpaRepository<CategoryLEntity, Long> {
}
