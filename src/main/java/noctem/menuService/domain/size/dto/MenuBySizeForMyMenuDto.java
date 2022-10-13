package noctem.menuService.domain.size.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuBySizeForMyMenuDto {

    private Long myMenuId;
    private String menuName;
    private String menuImg;
    private String temperature;
    private Integer totalMenuPrice; // 현재 퍼스널 옵션을 제외한 가격임
    private String size;

}
