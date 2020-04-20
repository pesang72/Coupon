package du.kakaocrop.coupon.api.service;

import java.util.List;

import du.kakaocrop.coupon.api.dto.BaseResDto;
import du.kakaocrop.coupon.api.domain.CouponInfo;


public interface CouponManageService {

    // 쿠폰 등록
    public CouponInfo registerCoupon(CouponInfo couponInfo);

    //1번 api
    public BaseResDto createCoupon(Long couponSeq, Long createCount);

    //6번 api
    public List<CouponInfo> getExpiredCoupon();

    public List<CouponInfo> getExpiredCoupon(Integer fromDays, Integer fromMinutes, Integer toDays, Integer toMinutes);

}
