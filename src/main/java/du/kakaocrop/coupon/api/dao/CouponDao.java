package du.kakaocrop.coupon.api.dao;

import java.sql.Timestamp;
import  java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;

public interface CouponDao {
    public Integer provideCoupon(Long userSeq, Long couponSeq);

    List<CouponInfo> getExpiredCoupon(Timestamp start_tmp, Timestamp end_tmp);

    public Long registerCoupon(CouponInfo couponInfo);

    public Integer existPrefix(String prefix);

    public CouponInfo getCouponInfo(Long couponSeq);

    public Integer updateCouponCountAndStatus(Long couponSeq, Long createCount, String y);
}
