package noctem.menuService.domain.personalOption.dto;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;

import javax.persistence.ManyToOne;

@Data
public class PersonalOptionReqDto {

    private String optionType;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean essential;

    private MenuEntity menuEntity;
}
