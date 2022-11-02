package noctem.menuService.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {
//    private final RedisTemplate<String, Integer> redisTemplate;
    private final RedisTemplate<String, String> redisStringTemplate;

    private final String MENU_KEY_PREFIX = "categorySmall";

    public String getMenuList(Long categorySId) {
        String key = String.format("%s:%d:menu", MENU_KEY_PREFIX, categorySId);
        return redisStringTemplate.opsForValue().get(key);
    }
}
