package noctem.menuService.domain.menu.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class RequestMenu {

    private String description;
    private String allergy;
    private Integer price;
    private boolean isSoldOut;
    private boolean isEos;
}
