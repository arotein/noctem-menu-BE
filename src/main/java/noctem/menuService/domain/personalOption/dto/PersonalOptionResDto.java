package noctem.menuService.domain.personalOption.dto;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;

import javax.persistence.ManyToOne;

@Data
public class PersonalOptionResDto {

    private String optionType;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean essential;

    public PersonalOptionResDto(String optionType, String optionName, String amount, Integer extraCost, Boolean essential) {
        this.optionType = optionType;
        this.optionName = optionName;
        this.amount = amount;
        this.extraCost = extraCost;
        this.essential = essential;
    }
}
