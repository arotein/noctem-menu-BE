package noctem.menuService.domain.categoryL.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.categoryS.entity.CategorySEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categoryl")
@EqualsAndHashCode(callSuper=false)
public class CategoryLEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryLName;

    @OneToMany(mappedBy = "categoryLEntity")
    @JsonManagedReference // 순환참조 방지
    private List<CategorySEntity> categorySEntityList = new ArrayList<>();
}
