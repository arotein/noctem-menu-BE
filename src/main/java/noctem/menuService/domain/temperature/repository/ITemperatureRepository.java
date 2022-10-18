package noctem.menuService.domain.temperature.repository;

import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITemperatureRepository extends JpaRepository<TemperatureEntity, Long> {

    @Query("select t from TemperatureEntity t " +
            "join t.menuEntity m " +
            "where m.id = :menuId"
    )
    List<TemperatureEntity> findTempByMenu(@Param("menuId") Long menuId);

    @Query(value = "SELECT * " +
            "FROM temperature t " +
            "join menu m " +
            "on m.id = t.menu_id " +
            "join categorys s " +
            "on s.id = m.categorys_id " +
            "where s.id = :categorySId and t.temperature = :temperature",
            nativeQuery = true
    )
    List<TemperatureEntity> findTempForAdmin(@Param("categorySId") Long categorySId, @Param("temperature") String temperature);
}
