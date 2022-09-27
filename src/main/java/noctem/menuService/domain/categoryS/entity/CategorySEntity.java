package noctem.menuService.domain.categoryS.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.categoryL.entity.CategoryLEntity;
import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categorys")
@EqualsAndHashCode(callSuper=false)
public class CategorySEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categorySName;

    @OneToMany(mappedBy = "categorySEntity")
    private List<MenuEntity> menuEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryLEntity_id")
    private CategoryLEntity categoryLEntity;
}
