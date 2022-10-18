package noctem.menuService.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MenuForAdminDto {

    private Long menuId;
    private String name;
    private String imgUrl;
}
