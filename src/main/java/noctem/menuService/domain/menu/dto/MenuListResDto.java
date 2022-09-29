package noctem.menuService.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Getter
public class MenuListResDto {
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer price;

    public MenuListResDto(List<TemperatureEntity> temperatureEntityList, Integer price, String temPolicy) {
        this.price = price;

        if (temperatureEntityList.size() == 1) {
            this.menuName = temperatureEntityList.get(0).getMenuName();
            this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
            this.menuImg = temperatureEntityList.get(0).getMenuImg();
        } else {
            if (temperatureEntityList.get(0).getTemperature().equals(temPolicy)) {
                this.menuName = temperatureEntityList.get(0).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            } else {
                this.menuName = temperatureEntityList.get(1).getMenuName();
                this.menuEngName = temperatureEntityList.get(0).getMenuEngName();
                this.menuImg = temperatureEntityList.get(0).getMenuImg();
            }
        }
    }
}
