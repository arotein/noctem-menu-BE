package noctem.menuService.domain.menu.dto;

import lombok.Data;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;

@Data
public class CartAndOptionListResServDto {

//    private Long sizeId;
//    private String menuName;
//    private String menuEngName;
//    private String menuImg;
//    private Integer totalPrice;
////    private List<PersonalOptionEntity> personalOptionEntityList = new ArrayList<>();
//
//
//    public CartAndOptionResServDto(Long sizeId, String menuName,
//                                   String menuEngName, String menuImg, Integer totalPrice) {
//        this.sizeId = sizeId;
//        this.menuName = menuName;
//        this.menuEngName = menuEngName;
//        this.menuImg = menuImg;
//        this.totalPrice = totalPrice;
//    }

    private Long cartOrMyMenuId;

    private Long sizeId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer totalPrice;

    public CartAndOptionListResServDto(Object o) {
    }

    public CartAndOptionListResServDto changeMenuIdAndSizeId(Long cartOrMyMenuId, Long sizeId) {
        this.cartOrMyMenuId = cartOrMyMenuId;
        this.sizeId = sizeId;
        return this;
    }

    public CartAndOptionListResServDto changeMenuNameAndImgAndPrice(SizeEntity sizeEntity) {
        TemperatureEntity tempEntity = sizeEntity.getTemperatureEntity();
        this.menuName = tempEntity.getMenuName();
        this.menuEngName = tempEntity.getMenuEngName();
        this.menuImg = tempEntity.getMenuImg();
        this.totalPrice = tempEntity.getMenuEntity().getPrice() + sizeEntity.getExtraCost();
        return this;
    }
}
