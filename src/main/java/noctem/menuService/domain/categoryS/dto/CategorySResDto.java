package noctem.menuService.domain.categoryS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CategorySResDto {

    private Integer index;
    private Long categorySId;
    private String CategorySName;
    private String categorySImg;

    public CategorySResDto(Long categorySId, String categorySName, String categorySImg) {
        this.categorySId = categorySId;
        this.CategorySName = categorySName;
        this.categorySImg = categorySImg;
    }
}
