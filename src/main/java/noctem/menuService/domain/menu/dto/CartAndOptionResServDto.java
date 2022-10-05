package noctem.menuService.domain.menu.dto;

import lombok.Data;

@Data
public class CartAndOptionResServDto {

    private String menuName;
    private String menuEngName;
    private String menuImg;
//    private PersonalOptionEntity personalOptionEntity;


    public CartAndOptionResServDto(String menuName, String menuEngName, String menuImg) {
        this.menuName = menuName;
        this.menuEngName = menuEngName;
        this.menuImg = menuImg;
    }
}
