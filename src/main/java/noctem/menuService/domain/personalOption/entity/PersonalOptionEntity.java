package noctem.menuService.domain.personalOption.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "personaloption")
@Data
@EqualsAndHashCode(callSuper=false)
public class PersonalOptionEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionType;
    private Integer optionNumber;
    private String optionName;
    private String amount;
    private Integer extraCost;
    private Boolean isEssential;
    private Boolean isDefault;

    @ManyToOne (fetch = FetchType.LAZY)
    private MenuEntity menuEntity;

}
