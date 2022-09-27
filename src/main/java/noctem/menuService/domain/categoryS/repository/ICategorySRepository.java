package noctem.menuService.domain.categoryS.repository;

import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategorySRepository extends JpaRepository<CategorySEntity, Long> {
}
