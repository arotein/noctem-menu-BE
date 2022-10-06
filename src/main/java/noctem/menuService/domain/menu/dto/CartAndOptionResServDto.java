package noctem.menuService.domain.menu.dto;

import lombok.Data;

@Data
public class CartAndOptionResServDto {

    private Long sizeId;
    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer totalPrice;
//    private List<PersonalOptionEntity> personalOptionEntityList = new ArrayList<>();


    public CartAndOptionResServDto(Long sizeId, String menuName,
                                   String menuEngName, String menuImg, Integer totalPrice) {
        this.sizeId = sizeId;
        this.menuName = menuName;
        this.menuEngName = menuEngName;
        this.menuImg = menuImg;
        this.totalPrice = totalPrice;
    }
}
