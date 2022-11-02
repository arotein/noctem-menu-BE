package noctem.menuService.domain.redis;

public interface RedisRepository {
    String getMenuList(Long storeId);
}
