package du.kakaocrop.coupon.api.dao;

import du.kakaocrop.coupon.api.domain.UserInfo;

public interface SignDao {
    public UserInfo findUserInfo(String email);

    public UserInfo findUserInfoBySeq(Long userSeq);
}
