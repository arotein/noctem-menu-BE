package noctem.menuService.domain.size.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
@AllArgsConstructor
public class SizeByTempResDto {

    private Long sizeId;
    private String size;
    private Integer extraCost;
}
