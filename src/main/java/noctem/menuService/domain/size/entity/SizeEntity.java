package noctem.menuService.domain.size.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import noctem.menuService.global.BaseEntity;

import javax.persistence.*;

@Entity
@Data
@Table(name = "size")
@EqualsAndHashCode(callSuper=false)
public class SizeEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private Integer extraCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temperature_id")
    @JsonIgnore
    private TemperatureEntity temperatureEntity;

}
