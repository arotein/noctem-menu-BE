package noctem.menuService.domain.menu.dto;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.temperature.dto.TemperatureWithSizeListResDto;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MenuDetailResDto {

    private Long menuId;
    private Integer price;
    private String allergy;

//    @JsonIgnore
//    private NutritionEntity nutritionList;
//    private List<PersonalOptionListResDto> personalOptionList;

    private List<TemperatureWithSizeListResDto> temperatureList;

//    public MenuDetailResDto(Long menuId, Integer price, String allergy, NutritionEntity nutritionList,
////                            List<PersonalOptionListResDto> personalOptionList,
//                            List<TemperatureWithSizeListResDto> temperatureList
//    ) {
//        this.menuId = menuId;
//        this.price = price;
//        this.allergy = allergy;
//        this.nutritionList = nutritionList;
////        this.personalOptionList = personalOptionList;
//        this.temperatureList = temperatureList;
//    }


    public MenuDetailResDto(MenuEntity menuEntity) {
        this.menuId = menuEntity.getId();
        this.price = menuEntity.getPrice();
        this.allergy = menuEntity.getAllergy();
//        this.nutritionList = null;
        List<TemperatureEntity> temperatureEntityList = menuEntity.getTemperatureEntityList();
        this.temperatureList = temperatureEntityList.stream()
                .map(e -> new TemperatureWithSizeListResDto(e)).collect(Collectors.toList());
        temperatureList.forEach(e -> e.setIndex(temperatureList.indexOf(e)));
    }
}
