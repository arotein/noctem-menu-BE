package noctem.menuService.domain.personalOption.dto;

import lombok.Data;

@Data
public class PersonalOptionListResDto {

    private Long id;
    private String optionType;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean essential;



}
