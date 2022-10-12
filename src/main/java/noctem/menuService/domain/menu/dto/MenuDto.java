package noctem.menuService.domain.menu.dto;

import lombok.Data;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuDto {

    private Long id;
    private String name;
    private String allergy;
    private Integer price;
//    private boolean isSoldOut;
//    private boolean isEos;

    private List<TemperatureEntity> temperatureEntityList = new ArrayList<>();

    private CategorySEntity categorySEntity;
}
