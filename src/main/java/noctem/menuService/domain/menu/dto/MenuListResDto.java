package noctem.menuService.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Getter
@Setter
public class MenuListResDto {

    private Integer index;
    private Long menuTemperatureId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer price;

    public MenuListResDto(List<TemperatureEntity> temperatureEntityList, Integer price, String temPolicy) {
        this.price = price;

        if (temperatureEntityList.size() == 1) {
            this.menuTemperatureId = temperatureEntityList.get(0).getId();
            this.menuName = temperatureEntityList.get(0).getMenuName();
            this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
            this.menuImg = temperatureEntityList.get(0).getMenuImg();
        } else {
            if (temperatureEntityList.get(0).getTemperature().equals(temPolicy)) {
                this.menuTemperatureId = temperatureEntityList.get(0).getId();
                this.menuName = temperatureEntityList.get(0).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            } else {
                this.menuTemperatureId = temperatureEntityList.get(0).getId();
                this.menuName = temperatureEntityList.get(1).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            }
        }
    }
}
