package du.kakaocrop.coupon.api.controller;

import du.kakaocrop.coupon.api.constant.Attributes;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserInfo;
import du.kakaocrop.coupon.api.domain.error.InvalidParamException;
import du.kakaocrop.coupon.api.dto.BaseResDto;
import du.kakaocrop.coupon.api.dto.res.CouponInfoResDto;
import du.kakaocrop.coupon.api.dto.res.ResponseDTO;
import du.kakaocrop.coupon.api.dto.res.ResponseListDto;
import du.kakaocrop.coupon.api.dto.res.UserCouponInfoResDto;
import du.kakaocrop.coupon.api.service.CouponService;
import du.kakaocrop.coupon.api.util.ResponseSupportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/coupon")
public class UserServiceController {


    private final CouponService couponService;

    @Autowired
    public UserServiceController(CouponService couponService) {
        this.couponService = couponService;
    }

    // 2.생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요.
    @PostMapping("/provide")
    public ResponseDTO<CouponCode> provideCoupon(HttpServletRequest request, @RequestParam(value = "couponSeq") Long couponSeq) {
        UserInfo info = (UserInfo) request.getAttribute(Attributes.USER_INFO.name());
        if (couponSeq == null) {
            throw new InvalidParamException("Required couponSeq parameter");
        }
        return new ResponseDTO<>(couponService.provideCoupon(info.getUserSeq(), couponSeq));
    }


    // 3.사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요
    @GetMapping("/list")
    public ResponseListDto<UserCouponInfoResDto> getCoupon(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute(Attributes.USER_INFO.name());
        //convertUserCouponInfoResDtoList를 통해 service return 값이 null 이라도 빈값을 리턴사도록 수정.
        return new ResponseListDto<UserCouponInfoResDto>(ResponseSupportUtil.convertUserCouponInfoResDtoList(couponService.getUserCouponList(userInfo.getUserSeq())));
    }


    // 4.지급된 쿠폰중 하나를 사용하는 API를 구현하세요.
    @PostMapping("/use")
    public ResponseDTO<Void> useCoupon(HttpServletRequest request, @RequestParam(value = "couponSeq") Long couponSeq) {
        UserInfo info = (UserInfo) request.getAttribute(Attributes.USER_INFO.name());
        if (couponSeq == null) {
            throw new InvalidParamException("Required couponSeq parameter");
        }
        couponService.useCoupon(info.getUserSeq(), couponSeq);
        return new ResponseDTO<>();
    }

    // 5.지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요.
    @PostMapping("/retraction")
    public ResponseDTO<Void> retractionCoupon(HttpServletRequest request, @RequestParam(value = "couponSeq") Long couponSeq) {
        UserInfo info = (UserInfo) request.getAttribute(Attributes.USER_INFO.name());
        if (couponSeq == null) {
            throw new InvalidParamException("Required couponSeq parameter");
        }
        couponService.cancelUseCoupon(info.getUserSeq(), couponSeq);
        return new ResponseDTO<>();
    }
}
