package du.kakaocrop.coupon.api.dao;

import java.sql.Timestamp;
import  java.util.List;

import du.kakaocrop.coupon.api.dao.mapper.CouponMapper;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CouponDaoImpl implements CouponDao {

    private final CouponMapper couponMapper;

    public CouponDaoImpl(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Override
    public Integer provideCoupon(Long userSeq, Long couponSeq) {
        return couponMapper.provideCoupon(userSeq,couponSeq);
    }


    @Override
    public List<CouponInfo> getExpiredCoupon(Timestamp start_tmp, Timestamp end_tmp) {
        return couponMapper.getExpiredCouponWithTimestamp(start_tmp, end_tmp);
    }

    @Override
    public Long registerCoupon(CouponInfo couponInfo) {
        return couponMapper.registerCoupon(couponInfo);
    }

    @Override
    public Integer existPrefix(String prefix) {
        return couponMapper.existPrefix(prefix);
    }

    @Override
    public CouponInfo getCouponInfo(Long couponSeq) {
        return couponMapper.getCoupon(couponSeq);
    }

    @Override
    public Integer updateCouponCountAndStatus(Long couponSeq,Long createCount, String y) {
        return couponMapper.updateCouponCountAndStatus(couponSeq,createCount,y);
    }
}
