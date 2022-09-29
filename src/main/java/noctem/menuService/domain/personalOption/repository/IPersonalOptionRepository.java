package noctem.menuService.domain.personalOption.repository;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.personalOption.entity.PersonalOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonalOptionRepository extends JpaRepository<PersonalOptionEntity, Long> {

    @Query("select p from PersonalOptionEntity p " +
            "join p.menuEntity m " +
            "where m.id = :menuId"
    )
    List<PersonalOptionEntity> findPersonalOptionListByMenu(@Param("menuId") Long menuId);
}
