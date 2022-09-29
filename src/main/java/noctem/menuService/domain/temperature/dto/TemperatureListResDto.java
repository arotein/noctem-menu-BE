package noctem.menuService.domain.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Getter
public class TemperatureListResDto {
    private String temperature;

    public TemperatureListResDto(String temperature) {
        this.temperature = temperature;
    }


}
