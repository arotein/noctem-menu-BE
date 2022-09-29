package noctem.menuService.domain.menu.repository;

import lombok.RequiredArgsConstructor;
import noctem.menuService.domain.menu.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IMenuRepository2Impl implements IMenuRepository2 {
    private final EntityManager entityManager;

    @Override
    public List<MenuEntity> findMenuByCategoryS(Long categorySId) {
        return entityManager.createQuery("select distinct m from MenuEntity m " +
                        "join m.categorySEntity c " +
                        "join m.temperatureEntityList t " +
                        "where c.id = :categorySId", MenuEntity.class)
                .setParameter("categorySId", categorySId)
                .getResultList();
    }
}
