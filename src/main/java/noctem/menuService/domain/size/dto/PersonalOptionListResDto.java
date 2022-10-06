package noctem.menuService.domain.size.dto;

import lombok.Data;

@Data
public class PersonalOptionListResDto {

    private Integer index;
    private Long personalOptionId;
    private Long menuId;
    private String optionType;
    private Integer optionNumber;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean isEssential;
    private Boolean isDefault;

    public PersonalOptionListResDto(Long personalOptionId, Long menuId, String optionType, Integer optionNumber,
                                    String optionName, String amount, Integer extraCost, Boolean isEssential,
                                    Boolean isDefault) {
        this.personalOptionId = personalOptionId;
        this.menuId = menuId;
        this.optionType = optionType;
        this.optionNumber = optionNumber;
        this.optionName = optionName;
        this.amount = amount;
        this.extraCost = extraCost;
        this.isEssential = isEssential;
        this.isDefault = isDefault;
    }
}
