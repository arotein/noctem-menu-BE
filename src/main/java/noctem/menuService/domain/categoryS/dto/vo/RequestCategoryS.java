package noctem.menuService.domain.categoryS.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class RequestCategoryS {

    private String categorySName;

    private CategoryLEntity categoryLEntity;

}
