package du.kakaocrop.coupon.api.dao.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {
    public void dropCouponCodeTable(@Param("couponSeq") Long couponSeq);

    public void dropCouponInfo(@Param("couponSeq") Long couponSeq);

    public void dropPrefixData(@Param("couponSeq") Long couponSeq);

    public void dropCountData(@Param("couponSeq") Long couponSeq);

    public void dropUserData(@Param("userSeq") Long userSeq,@Param("couponSeq") Long couponSeq);

    public Long getCouponCodeRowCount(@Param("couponSeq") Long couponSeq);
}
