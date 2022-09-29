package noctem.menuService.domain.temperature.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class RequestTemperature {

    private String temperature;
    private String menuName;
    private String menuShortenName;
    private String description;
    private String img;

    private MenuEntity menuEntity;
}
