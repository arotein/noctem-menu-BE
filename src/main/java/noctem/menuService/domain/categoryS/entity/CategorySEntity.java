package noctem.menuService.domain.categoryS.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String categorySImg;

    @OneToMany(mappedBy = "categorySEntity")
    @JsonIgnore
    private List<MenuEntity> menuEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference //순환참조 방지
    private CategoryLEntity categoryLEntity;
}
