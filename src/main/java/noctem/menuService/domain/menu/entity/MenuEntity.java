package noctem.menuService.domain.menu.entity;

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
    private String description;
    private String allergy;
    private Integer price;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isSoldOut;
    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isEos;

    @OneToMany(mappedBy = "menuEntity")
    private List<TemperatureEntity> temperatureEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temperature_id")
    private CategorySEntity categorySEntity;
}
