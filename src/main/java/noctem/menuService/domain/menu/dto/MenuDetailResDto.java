package noctem.menuService.domain.menu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import noctem.menuService.domain.nutrition.entity.NutritionEntity;
import noctem.menuService.domain.personalOption.entity.PersonalOptionEntity;
import noctem.menuService.domain.temperature.dto.TemperatureListResDto;
import noctem.menuService.domain.temperature.dto.TemperatureWithSizeListResDto;

import java.util.List;

@Data
public class MenuDetailResDto {

    private Long menuId;
    private Integer price;
    private String allergy;

    @JsonIgnore
    private NutritionEntity nutritionList;
//    private List<PersonalOptionListResDto> personalOptionList;

    private List<TemperatureWithSizeListResDto> temperatureList;

    public MenuDetailResDto(Long menuId, Integer price, String allergy, NutritionEntity nutritionList,
//                            List<PersonalOptionListResDto> personalOptionList,
                            List<TemperatureWithSizeListResDto> temperatureList
    ) {
        this.menuId = menuId;
        this.price = price;
        this.allergy = allergy;
        this.nutritionList = nutritionList;
//        this.personalOptionList = personalOptionList;
        this.temperatureList = temperatureList;
    }
}
