package noctem.menuService.domain.temperature.dto;

import lombok.Data;
import noctem.menuService.domain.size.dto.SizeResDto;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;
import java.util.stream.Collectors;

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

//    public TemperatureWithSizeListResDto(Long temperatureId, String menuName, String menuEngName,
//                                         String description, String menuImg, String temperature
////                                        , List<SizeResDto> sizeList
//    ) {
//        this.temperatureId = temperatureId;
//        this.menuName = menuName;
//        this.menuEngName = menuEngName;
//        this.description = description;
//        this.menuImg = menuImg;
//        this.temperature = temperature;
////        this.sizeList = sizeList;
//    }


    public TemperatureWithSizeListResDto(TemperatureEntity temperatureEntity) {
        this.temperatureId = temperatureEntity.getId();
        this.menuName = temperatureEntity.getMenuName();
        this.menuEngName = temperatureEntity.getMenuEngName();
        this.description = temperatureEntity.getDescription();
        this.menuImg = temperatureEntity.getMenuImg();
        this.temperature = temperatureEntity.getTemperature();
        List<SizeEntity> sizeEntityList = temperatureEntity.getSizeEntityList();
        this.sizeList = sizeEntityList.stream()
                .map(e -> new SizeResDto(e)).collect(Collectors.toList());
        sizeList.forEach(e -> e.setIndex(sizeList.indexOf(e)));
    }
}
