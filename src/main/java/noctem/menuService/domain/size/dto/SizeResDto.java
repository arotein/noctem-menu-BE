package noctem.menuService.domain.size.dto;

import lombok.Data;
import noctem.menuService.domain.size.entity.SizeEntity;

@Data
public class SizeResDto {

    private Integer index;
    private Long id;
    private String size;
    private Integer extraCost;

    public SizeResDto(SizeEntity sizeEntity) {
        this.id = sizeEntity.getId();
        this.size = sizeEntity.getSize();
        this.extraCost = sizeEntity.getExtraCost();
    }
}
