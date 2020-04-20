package du.kakaocrop.coupon.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CouponPrefixMapper {
    public String getPrefix(@Param("couponSeq")Long couponSeq);
}
