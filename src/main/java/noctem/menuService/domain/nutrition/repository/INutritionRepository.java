package noctem.menuService.domain.nutrition.repository;

import noctem.menuService.domain.nutrition.entity.NutritionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INutritionRepository extends JpaRepository<NutritionEntity, Long> {

}
