package noctem.menuService.domain.size.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class SizeReqDto {

    private Long id;
    private String size;
    private Integer extraCost;
    private Integer capacity;

    private TemperatureEntity temperatureEntity;
}
