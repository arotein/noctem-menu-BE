package noctem.menuService.domain.categoryS.dto.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null 데이터는 json에 안 띄움
public class ResponseCategoryS {

    private Long id;
    private String categorySName;

    @JsonIgnore
    private CategoryLEntity categoryLEntity;

    private List<MenuEntity> menuEntityList;

    private List<TemperatureEntity> temperatureEntityList;
}
