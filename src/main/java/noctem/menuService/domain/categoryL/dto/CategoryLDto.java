package noctem.menuService.domain.categoryL.dto;

import lombok.Data;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryLDto {

    private Long id;
    private String categoryLName;

//    private List<CategorySEntity> categorySEntityList = new ArrayList<>();
}
