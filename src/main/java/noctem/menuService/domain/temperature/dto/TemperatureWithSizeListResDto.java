package noctem.menuService.domain.temperature.dto;

import lombok.Data;
import noctem.menuService.domain.size.dto.SizeResDto;

import java.util.List;

@Data
public class TemperatureWithSizeListResDto {

    private Integer index;
    private Long temperatureId;
    private String menuName;
    private String menuEngName;
    private String description;
    private String menuImg;
    private String temperature;

    private List<SizeResDto> sizeList;

    public TemperatureWithSizeListResDto(Long temperatureId, String menuName, String menuEngName,
                                         String description, String menuImg, String temperature
//                                        , List<SizeResDto> sizeList
    ) {
        this.temperatureId = temperatureId;
        this.menuName = menuName;
        this.menuEngName = menuEngName;
        this.description = description;
        this.menuImg = menuImg;
        this.temperature = temperature;
//        this.sizeList = sizeList;
    }
}
