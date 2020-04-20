package du.kakaocrop.coupon.api.service;

import  java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import du.kakaocrop.coupon.api.dto.BaseResDto;

public interface CouponService {

    // 2번 API 쿠폰 발급.
    public CouponCode provideCoupon(Long userSeq, Long couponSeq);

    // 3번 API 발급된 쿠폰 조회
    public List<UserCouponInfo> getUserCouponList(Long userSeq);

    //4번 API 쿠폰 사용
    public void useCoupon(Long userSeq, Long couponSeq);

    //5번 API 쿠폰 사용 취소.
    public void cancelUseCoupon(Long userSeq, Long couponSeq);

}
