package noctem.menuService.domain.temperature.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.size.entity.SizeEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "temperature")
@EqualsAndHashCode(callSuper=false)
public class TemperatureEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String temperature;
    private String menuName;
    private String menuShortenName;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menuEntity;

    @OneToMany(mappedBy = "temperatureEntity")
    private List<SizeEntity> sizeEntityList = new ArrayList<>();

}
