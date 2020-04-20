package du.kakaocrop.coupon.api.dao;

import du.kakaocrop.coupon.api.dao.mapper.CouponUserMapper;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponUserDaoImpl implements CouponUserDao {

    private final CouponUserMapper couponUserMapper;

    public CouponUserDaoImpl(CouponUserMapper couponUserMapper) {
        this.couponUserMapper = couponUserMapper;
    }

    @Override
    public List<UserCouponInfo> getUserCouponList(Long userSeq) {
        return couponUserMapper.getUserCouponList(userSeq);
    }

    @Override
    public UserCouponInfo getUserCouponStatus(Long userSeq, Long couponSeq) {
        return couponUserMapper.getUserCoupon(userSeq,couponSeq);
    }

    @Override
    public boolean processCoupon(Long userSeq, Long couponSeq) {
        return couponUserMapper.processCoupon(userSeq,couponSeq) <= 0 ? false : true;
    }

    @Override
    public boolean cancelUseCoupon(Long userSeq, Long couponSeq) {
        return couponUserMapper.cancelUseCoupon(userSeq,couponSeq) > 0 ? true : false;
    }
}
