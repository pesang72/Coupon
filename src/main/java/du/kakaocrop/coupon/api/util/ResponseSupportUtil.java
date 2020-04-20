package du.kakaocrop.coupon.api.util;

import java.util.ArrayList;
import java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import du.kakaocrop.coupon.api.dto.res.CouponInfoResDto;
import du.kakaocrop.coupon.api.dto.res.UserCouponInfoResDto;

public class ResponseSupportUtil {

    public static List<UserCouponInfoResDto> convertUserCouponInfoResDtoList(List<UserCouponInfo> list) {
        // Input list가 빈값인 경우 빈 리스트를 리턴.
        List<UserCouponInfoResDto> res = new ArrayList<>();
        for(UserCouponInfo info : list){
            res.add(new UserCouponInfoResDto(info));
        }
        return res;
    }

    public static CouponInfoResDto convertCouponInfoResDto(CouponInfo info) {
        return new CouponInfoResDto(info);
    }
}
