package noctem.menuService.domain.size.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuBySizeForCartDto {

    private Long cartId;
    private Long menuId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private String temperature;
    private Integer totalMenuPrice; // 현재 퍼스널 옵션을 제외한 가격임
    private String size;

}
