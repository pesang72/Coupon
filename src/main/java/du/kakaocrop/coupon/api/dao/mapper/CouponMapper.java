package du.kakaocrop.coupon.api.dao.mapper;

import java.sql.Timestamp;
import java.util.List;

import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CouponMapper {
    public CouponInfo getCoupon(@Param("couponSeq") Long couponSeq);
    public Integer provideCoupon(Long userSeq, Long couponSeq);
    public List<CouponInfo> getExpiredCouponWithTimestamp(@Param("start_tmp") Timestamp start_tmp,@Param("end_tmp") Timestamp end_tmp);
    public Long registerCoupon(CouponInfo couponInfo);

    public Integer existPrefix(@Param("prefix")  String prefix);

    public Integer updateCouponCountAndStatus(@Param("couponSeq") Long couponSeq,@Param("createCount") Long createCount,@Param("y") String y);
}
