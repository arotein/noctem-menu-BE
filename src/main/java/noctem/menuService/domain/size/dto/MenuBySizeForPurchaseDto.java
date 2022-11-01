package noctem.menuService.domain.size.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuBySizeForPurchaseDto {

    private Long cartId;
    private String categorySmall;
    private String menuFullName;
    private String menuShortName;
    private Integer menuTotalPrice;
    private String imgUrl;
}
