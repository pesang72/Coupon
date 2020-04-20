package du.kakaocrop.coupon.api.dao;

import java.util.List;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;

public interface CouponUserDao {
    public List<UserCouponInfo> getUserCouponList(Long userSeq);

    public boolean processCoupon(Long userSeq, Long couponSeq);

    public UserCouponInfo getUserCouponStatus(Long userSeq, Long couponSeq);

    public boolean cancelUseCoupon(Long userSeq, Long couponSeq);
}
