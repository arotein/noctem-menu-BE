package noctem.menuService.domain.size.dto;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
public class SizeDto {

    private Long id;
    private String size;
    private Integer extraCost;

    private TemperatureEntity temperatureEntity;
}
