package du.kakaocrop.coupon.api.dao.mapper;


import java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

@Mapper
public interface CouponCodeMapper {

    public CouponCode getCouponCode(@Param("couponSeq") Long couponSeq, @Param("couponUniqueSeq") Long couponUniqueSeq);

    public void getCouponCodeList(@Param("couponSeq") Long couponSeq,ResultHandler handler);

    public Integer insertCouponCode(@Param("couponCode") CouponCode couponCode);

    public Integer processCouponCode(Long couponSeq, Long couponCodeSeq);

    public Integer cancelUseCouponCode(Long couponSeq, Long couponCodeSeq);

    public Integer insertCouponCodeList(@Param("couponSeq")Long couponSeq, @Param("couponCodes") List<CouponCode> couponCodes);
}
