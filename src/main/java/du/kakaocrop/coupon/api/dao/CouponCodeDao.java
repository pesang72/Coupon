package du.kakaocrop.coupon.api.dao;

import java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;

public interface CouponCodeDao {

    public CouponCode getCouponCode(Long couponSeq,Long couponUniqueSeq);

    public void getCouponCodeList(Long couponSeq,ResultHandler handler);

    public Integer insertCouponCode(CouponCode couponCode);

    public Integer insertCouponCode(Long couponSeq,List<CouponCode> couponCodes);

    public Boolean processCouponCode(Long couponSeq, Long couponCodeSeq);

    public boolean cancelUseCouponCode(Long couponSeq, Long couponCodeSeq);
}
