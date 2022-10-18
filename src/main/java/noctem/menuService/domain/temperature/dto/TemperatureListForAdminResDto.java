package noctem.menuService.domain.temperature.dto;

import lombok.Data;

@Data
public class TemperatureListForAdminResDto {

    private Integer index;
    private Long temperatureId;
    private String menuName;
    private String menuImg;

    public TemperatureListForAdminResDto(Long temperatureId, String menuName, String menuImg) {
        this.temperatureId = temperatureId;
        this.menuName = menuName;
        this.menuImg = menuImg;
    }
}
