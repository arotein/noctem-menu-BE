package noctem.menuService.domain.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Getter
@AllArgsConstructor
public class TemperatureListResDto {
    private Long temperatureId;
    private String temperature;
}
