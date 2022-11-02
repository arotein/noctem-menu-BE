package noctem.menuService.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuListResDto {

    private Integer index;
    private Long menuId;
    private Long menuTemperatureId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer price;

    public MenuListResDto(Long menuId, List<TemperatureEntity> temperatureEntityList, Integer price, String temPolicy) {
        this.price = price;

        if (temperatureEntityList.size() == 1) {
            this.menuId = menuId;
            this.menuTemperatureId = temperatureEntityList.get(0).getId();
            this.menuName = temperatureEntityList.get(0).getMenuName();
            this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
            this.menuImg = temperatureEntityList.get(0).getMenuImg();
        } else {
            if (temperatureEntityList.get(0).getTemperature().equals(temPolicy)) {
                this.menuId = menuId;
                this.menuTemperatureId = temperatureEntityList.get(0).getId();
                this.menuName = temperatureEntityList.get(0).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            } else {
                this.menuId = menuId;
                this.menuTemperatureId = temperatureEntityList.get(0).getId();
                this.menuName = temperatureEntityList.get(1).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            }
        }
    }
}
