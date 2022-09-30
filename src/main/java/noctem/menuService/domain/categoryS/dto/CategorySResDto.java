package noctem.menuService.domain.categoryS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategorySResDto {

    private Long categorySId;
    private String CategorySName;
    private String categorySImg;

}
