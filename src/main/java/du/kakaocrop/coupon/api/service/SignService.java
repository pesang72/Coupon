package du.kakaocrop.coupon.api.service;

import du.kakaocrop.coupon.api.domain.UserInfo;

public interface SignService {
    public UserInfo signIn(String email, String password);

    public UserInfo getUserInfo(Long userSeq);
}
