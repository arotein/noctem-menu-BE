package noctem.menuService.domain.categoryL.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class ResponseCategoryL {

    private Long id;
    private String categoryLName;

    private List<CategorySEntity> categorySEntityList;
}
