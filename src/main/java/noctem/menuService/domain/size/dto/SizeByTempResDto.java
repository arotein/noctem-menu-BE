package noctem.menuService.domain.size.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class SizeByTempResDto {

    private Integer index;
    private Long sizeId;
    private String size;
    private Integer extraCost;
    private Integer capacity;

    public SizeByTempResDto(Long sizeId, String size, Integer extraCost, Integer capacity) {
        this.sizeId = sizeId;
        this.size = size;
        this.extraCost = extraCost;
        this.capacity = capacity;
    }
}
