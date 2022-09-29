package noctem.menuService.domain.menu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "menu")
@EqualsAndHashCode(callSuper=false)
public class MenuEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String allergy;
    private Integer price;
    private Boolean isSoldOut = false;
    private Boolean isEos = false;

    @JsonIgnore
    @OneToMany(mappedBy = "menuEntity")
    private List<TemperatureEntity> temperatureEntityList = new ArrayList<>();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "categorys_id")
    private CategorySEntity categorySEntity;
}
