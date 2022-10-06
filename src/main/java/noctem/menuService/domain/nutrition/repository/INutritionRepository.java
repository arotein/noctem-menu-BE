package noctem.menuService.domain.nutrition.repository;

import noctem.menuService.domain.nutrition.entity.NutritionEntity;
import noctem.menuService.domain.size.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INutritionRepository extends JpaRepository<NutritionEntity, Long> {


    @Query("select n from NutritionEntity n " +
            "join n.menuEntity m " +
            "where m.id = :menuId"
    )
    NutritionEntity findNutritionListByMenu(@Param("menuId") Long menuId);
}
