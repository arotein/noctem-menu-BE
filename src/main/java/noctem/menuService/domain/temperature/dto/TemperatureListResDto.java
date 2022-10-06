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
    private Long menuId;
    private String menuName;
    private String menuEngName;
    private String description;
    private String menuImg;
    private String temperature;
    private Integer price;

    public TemperatureListResDto(Long temperatureId, Long menuId, String menuName, String menuEngName, String description, String menuImg, String temperature, Integer price) {
        this.temperatureId = temperatureId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuEngName = menuEngName;
        this.description = description;
        this.menuImg = menuImg;
        this.temperature = temperature;
        this.price = price;
    }
}
