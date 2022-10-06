package noctem.menuService.domain.menu.dto;

import lombok.Data;

@Data
public class CartAndOptionResServDto {

    private String menuName;
    private String menuEngName;
    private String menuImg;
    private Integer totalPrice;
//    private List<PersonalOptionEntity> personalOptionEntityList = new ArrayList<>();


    public CartAndOptionResServDto(String menuName, String menuEngName, String menuImg, Integer totalPrice) {
        this.menuName = menuName;
        this.menuEngName = menuEngName;
        this.menuImg = menuImg;
        this.totalPrice = totalPrice;
    }
}
