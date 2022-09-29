package noctem.menuService.domain.menu.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class ResponseMenu {

    private Long id;
    private String allergy;
    private Integer price;
    private boolean isSoldOut;
    private boolean isEos;

    private List<TemperatureEntity> temperatureEntityList = new ArrayList<>();

    @JsonIgnore
    private CategorySEntity categorySEntity;
}
