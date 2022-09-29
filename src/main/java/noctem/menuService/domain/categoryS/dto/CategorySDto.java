package noctem.menuService.domain.categoryS.dto;

import lombok.Data;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import noctem.menuService.domain.menu.entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategorySDto {

    private Long id;
    private String CategorySName;

//    private List<MenuEntity> menuEntityList = new ArrayList<>();
//    private CategoryLEntity categoryLEntity;
}
