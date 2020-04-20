package du.kakaocrop.coupon.api.service;

import du.kakaocrop.coupon.api.domain.CouponCode;

import java.io.IOException;

public interface AlarmService {
    public void getCouponCodes(Long couponSeq);
    public void sendAlarm(CouponCode couponCode);
}
