package noctem.menuService.domain.nutrition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name = "nutrition")
@EqualsAndHashCode(callSuper=false)
public class NutritionEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne//(mappedBy = "nutritionEntity")
    @JsonIgnore
    private MenuEntity menuEntity;
}
