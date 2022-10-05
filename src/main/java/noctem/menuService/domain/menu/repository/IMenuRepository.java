package noctem.menuService.domain.menu.repository;

import noctem.menuService.domain.menu.entity.MenuEntity;
import noctem.menuService.domain.temperature.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuRepository extends JpaRepository<MenuEntity, Long>, IMenuRepository2 {

//    @Query("select t.id, t.menuName, t.menuEngName, t.menuImg " +
//            "from SizeEntity s " +
//            "join s.temperatureEntity t " +
//            "join t.menuEntity m " +
//            "where s.id = :sizeId and t.id = s.temperatureEntity and m.id = t.menuEntity"
//    )
//    MenuEntity findMenuCartBySizeId(@Param("sizeId") Long sizeId);

//    -- 사이즈 id -> 온도 id -> 메뉴 id
//    select m.id as menu_id, m.allergy, t.id as temp_id, t.menu_name, t.menu_img, t.menu_name, t.temperature, s.id as size_id
//    from size s
//    join temperature t
//    join menu m
//    where t.id = s.temperature_id and s.id = 1 and m.id = t.menu_id;

//    -- 사이즈 id -> 온도 id -> 메뉴 id -> 퍼스널 id
//    select m.id as menu_id, m.allergy, t.id as temp_id, t.menu_name, t.menu_img, t.menu_name, t.temperature, s.id as size_id, p.id as po_id, p.option_type, p.option_name
//    from size s
//    join temperature t
//    join menu m
//    join personaloption p
//    where t.id = s.temperature_id and s.id = 1 and m.id = t.menu_id and m.id = p.menu_entity_id;



}
