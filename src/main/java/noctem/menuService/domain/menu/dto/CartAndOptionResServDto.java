package noctem.menuService.domain.menu.dto;

import lombok.Data;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class CartAndOptionResServDto {

    private Long cartOrMyMenuId;
    private Long sizeId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer totalPrice;

    public CartAndOptionResServDto setFieldData(Long cartOrMyMenuId, SizeEntity sizeEntity) {
        this.cartOrMyMenuId = cartOrMyMenuId;
        this.sizeId = sizeEntity.getId();
        TemperatureEntity tempEntity = sizeEntity.getTemperatureEntity();
        this.menuName = tempEntity.getMenuName();
        this.menuEngName = tempEntity.getMenuEngName();
        this.menuImg = tempEntity.getMenuImg();
        this.totalPrice = sizeEntity.getTemperatureEntity().getMenuEntity().getPrice() + sizeEntity.getExtraCost();
        return this;
    }
}
