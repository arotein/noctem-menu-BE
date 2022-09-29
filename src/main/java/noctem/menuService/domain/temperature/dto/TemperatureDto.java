package noctem.menuService.domain.temperature.dto;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.size.entity.SizeEntity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class TemperatureDto {

    private Long id;
    private String temperature;
    private String menuName;
    private String menuEngName;
    private String description;
    private String menuImg;

    private MenuEntity menuEntity;

    private List<SizeEntity> sizeEntityList = new ArrayList<>();
}
