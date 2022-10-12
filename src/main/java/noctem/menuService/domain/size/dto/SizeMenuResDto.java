package noctem.menuService.domain.size.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import noctem.menuService.domain.size.entity.SizeEntity;

@Data
@AllArgsConstructor
public class SizeMenuResDto {
    private Long sizeId;
    private Long menuId;
    private String menuKorName;
    private String menuShortenName;
    private Integer totalPrice;
}
