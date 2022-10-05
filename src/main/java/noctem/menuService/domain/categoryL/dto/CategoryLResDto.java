package noctem.menuService.domain.categoryL.dto;

import lombok.Data;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryLResDto {

    private Integer index;
    private Long id;
    private String categoryLName;
}
