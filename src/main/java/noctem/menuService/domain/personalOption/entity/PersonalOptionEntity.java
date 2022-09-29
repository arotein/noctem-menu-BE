package noctem.menuService.domain.personalOption.entity;

import lombok.Data;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "personaloption")
@Data
public class PersonalOptionEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionType;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean essential;

    @ManyToOne
    private MenuEntity menuEntity;

}
