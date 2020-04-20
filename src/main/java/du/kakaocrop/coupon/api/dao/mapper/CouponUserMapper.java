package du.kakaocrop.coupon.api.dao.mapper;

import java.util.List;

import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CouponUserMapper {
    public List<UserCouponInfo> getUserCouponList(@Param("userSeq") Long userSeq);

    public UserCouponInfo getUserCoupon(@Param("userSeq")Long userSeq,@Param("couponSeq") Long couponSeq);

    public Integer processCoupon(@Param("userSeq") Long userSeq, @Param("couponSeq") Long couponSeq);

    public Integer cancelUseCoupon(@Param("userSeq")Long userSeq,@Param("couponSeq") Long couponSeq);
}
