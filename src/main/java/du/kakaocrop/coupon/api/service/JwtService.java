package du.kakaocrop.coupon.api.service;

import java.util.Map;

public interface JwtService {
    <T> String create(String key, T data, String subject);

    Object get(String token, String key);

    boolean isUsable(String token);
}
