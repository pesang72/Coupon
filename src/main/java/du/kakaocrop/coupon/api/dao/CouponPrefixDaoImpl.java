package du.kakaocrop.coupon.api.dao;

import du.kakaocrop.coupon.api.dao.mapper.CouponPrefixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CouponPrefixDaoImpl implements CouponPrefixDao{

    private final CouponPrefixMapper couponPrefixMapper;

    public CouponPrefixDaoImpl(CouponPrefixMapper couponPrefixMapper) {
        this.couponPrefixMapper = couponPrefixMapper;
    }

    @Override
    public String getPrefix(Long couponSeq) {
        return couponPrefixMapper.getPrefix(couponSeq);
    }
}
