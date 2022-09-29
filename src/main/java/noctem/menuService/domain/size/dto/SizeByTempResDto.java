package noctem.menuService.domain.size.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class SizeByTempResDto {

    private String size;
    private Integer extraCost;

    public SizeByTempResDto(String size, Integer extraCost) {
        this.size = size;
        this.extraCost = extraCost;
    }
}
