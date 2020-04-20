package du.kakaocrop.coupon.api.dao;

import java.util.List;
import du.kakaocrop.coupon.api.dao.mapper.CouponCodeMapper;
import du.kakaocrop.coupon.api.domain.CouponCode;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCodeDaoImpl implements CouponCodeDao{

    private final CouponCodeMapper couponCodeMapper;

    public CouponCodeDaoImpl(CouponCodeMapper couponCodeMapper) {
        this.couponCodeMapper = couponCodeMapper;
    }

    @Override
    public CouponCode getCouponCode(Long couponSeq, Long couponUniqueSeq) {
        return couponCodeMapper.getCouponCode(couponSeq,couponUniqueSeq);
    }

    @Override
    public void getCouponCodeList(Long couponSeq, ResultHandler handler) {
         couponCodeMapper.getCouponCodeList(couponSeq,handler);
    }

    @Override
    public Integer insertCouponCode(CouponCode couponCode) {
        return couponCodeMapper.insertCouponCode(couponCode);
    }

    @Override
    public Integer insertCouponCode(Long couponSeq,List<CouponCode> couponCodes) {
        return couponCodeMapper.insertCouponCodeList(couponSeq, couponCodes);
    }

    @Override
    public Boolean processCouponCode(Long couponSeq, Long couponCodeSeq) {
        return couponCodeMapper.processCouponCode(couponSeq, couponCodeSeq) > 0 ? true : false;
    }

    @Override
    public boolean cancelUseCouponCode(Long couponSeq, Long couponCodeSeq) {
        return couponCodeMapper.cancelUseCouponCode(couponSeq,couponCodeSeq)> 0 ? true : false;
    }
}
