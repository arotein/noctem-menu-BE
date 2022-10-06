package noctem.menuService.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CartAndOptionsReqServDto {
    private Long cartOrMyMenuId;
    private Long sizeId;
    private List<Long> optionIdList;


    public CartAndOptionsReqServDto(Long sizeId) {
        this.sizeId = sizeId;
    }
}
