package noctem.menuService.domain.temperature.dto.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.size.entity.SizeEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class ResponseTemperature {

    private Long id;
    private String temperature;
    private String menuName;
    private String menuShortenName;
    private String description;
    private String img;

    private MenuEntity menuEntity;

    private List<SizeEntity> sizeEntityList = new ArrayList<>();

}
