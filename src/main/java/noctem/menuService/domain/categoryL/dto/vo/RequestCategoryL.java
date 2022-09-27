package noctem.menuService.domain.categoryL.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class RequestCategoryL {

    private String categoryLName;
}
