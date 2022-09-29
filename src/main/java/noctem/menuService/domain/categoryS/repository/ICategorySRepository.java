package noctem.menuService.domain.categoryS.repository;

import noctem.menuService.domain.categoryS.dto.CategorySDto;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategorySRepository extends JpaRepository<CategorySEntity, Long> {

    @Query("select s from CategorySEntity s " +
            "join s.categoryLEntity l " +
            "where l.id = :categoryLId"
    )
    List<CategorySEntity> findCategorySListByCategoryL(@Param("categoryLId") Long categoryLId);
}
