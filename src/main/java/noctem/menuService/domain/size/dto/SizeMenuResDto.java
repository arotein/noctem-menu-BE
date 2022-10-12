package noctem.menuService.domain.size.dto;

import lombok.Data;
import noctem.menuService.domain.size.entity.SizeEntity;

@Data
public class SizeMenuResDto {

    private Long sizeId;
    private Long menuId;
    private String menuKorName;
    private String menuShortenName;
    private Integer totalPrice;

    public SizeMenuResDto(Long sizeId, Long menuId, String menuKorName, String menuShortenName, Integer totalPrice) {
        this.sizeId = sizeId;
        this.menuId = menuId;
        this.menuKorName = menuKorName;
        this.menuShortenName = menuShortenName;
        this.totalPrice = totalPrice;
    }
}
