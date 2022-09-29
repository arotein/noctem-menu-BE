package noctem.menuService.domain.size.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class SizeResDto {

    private Long id;
    private String size;
    private Integer extraCost;

    @JsonIgnore
    private TemperatureEntity temperatureEntity;
}
