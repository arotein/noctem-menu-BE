package noctem.menuService.domain.categoryS.dto;

import lombok.Data;

@Data
public class CategorySResDto {
    private String CategorySName;
    private String categorySImg;

    public CategorySResDto(String categorySName, String categorySImg) {
        CategorySName = categorySName;
        this.categorySImg = categorySImg;
    }
}
