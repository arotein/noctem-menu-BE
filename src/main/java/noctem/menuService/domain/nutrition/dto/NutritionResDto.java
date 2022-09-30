package noctem.menuService.domain.nutrition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NutritionResDto {

    private Long id;
    private int kcal; // 칼로리
    private int carbohydrates; // 탄수화물
    private int sugars; // 당류
    private int sodium; // 나트륨
    private int protein; // 단백질
    private int fat; // 지방
    private int cholesterol; // 콜레스트롤
    private int transFat; // 트랜스지방
    private int caffeine; // 카페인
    private int saturatedFat; // 포화지방

}
