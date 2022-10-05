package noctem.menuService.domain.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Data
public class TemperatureListResDto {

    private Integer index;
    private Long temperatureId;
    private String temperature;

    public TemperatureListResDto(Long temperatureId, String temperature) {
        this.temperatureId = temperatureId;
        this.temperature = temperature;
    }
}
