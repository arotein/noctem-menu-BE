package noctem.menuService.domain.temperature.repository;

import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemperatureRepository extends JpaRepository<TemperatureEntity, Long> {
}
