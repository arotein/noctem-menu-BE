package noctem.menuService.domain.nutrition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NutritionResDto {

    private Long id;
    private double kcal; // 칼로리
    private double carbohydrates; // 탄수화물
    private double sugars; // 당류
    private double sodium; // 나트륨
    private double protein; // 단백질
    private double fat; // 지방
    private double cholesterol; // 콜레스트롤
    private double transFat; // 트랜스지방
    private double caffeine; // 카페인
    private double saturatedFat; // 포화지방

}
