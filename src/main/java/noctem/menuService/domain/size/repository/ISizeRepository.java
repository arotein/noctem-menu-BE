package noctem.menuService.domain.size.repository;

import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISizeRepository extends JpaRepository<SizeEntity, Long> {

    @Query("select s from SizeEntity s " +
            "join s.temperatureEntity t " +
            "where t.id = :temperatureId"
    )
    List<SizeEntity> findSizeListByTemp(@Param("temperatureId") Long temperatureId);

}
