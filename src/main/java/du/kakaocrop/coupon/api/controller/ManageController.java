package du.kakaocrop.coupon.api.controller;

import du.kakaocrop.coupon.api.dto.BaseResDto;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.error.InvalidParamException;
import du.kakaocrop.coupon.api.dto.req.RequestCouponDto;
import du.kakaocrop.coupon.api.dto.res.ResponseDTO;
import du.kakaocrop.coupon.api.dto.res.ResponseListDto;
import du.kakaocrop.coupon.api.service.CouponManageService;
import du.kakaocrop.coupon.api.service.CouponManageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/manage/coupon")
public class ManageController {
    private final CouponManageService manageService;

    public ManageController(CouponManageService manageService) {
        this.manageService = manageService;
    }

    // 쿠폰 등록 API
    @PostMapping("/create")
    public ResponseDTO<CouponInfo> createCoupon(RequestCouponDto reqCouponInfo) {
        if (reqCouponInfo.getEndDate() == null || reqCouponInfo.getStartDate() == null || reqCouponInfo.getThumbnail() == null || reqCouponInfo.getTitle() == null) {
            throw new InvalidParamException("Required Coupon Create Information");
        }

        return new ResponseDTO<CouponInfo>(manageService.registerCoupon(new CouponInfo(reqCouponInfo)));
    }


    // 1.랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요. input : N
    @PostMapping("/code/create")
    public ResponseDTO<String> createCouponCode(@RequestParam(value = "couponSeq") Long couponSeq,
                                                @RequestParam(value = "createCount") Long createCount) {
        if (couponSeq == null) {
            throw new InvalidParamException("Required CouponSeq parameter");
        }
        if (createCount == null) {
            throw new InvalidParamException("Required createCount parameter");
        }
        manageService.createCoupon(couponSeq, createCount);
        return new ResponseDTO<>();
    }


    // 6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요.
    @GetMapping("/expiration")
    public ResponseListDto<CouponInfo> getExpiration() {
        return new ResponseListDto<CouponInfo>(manageService.getExpiredCoupon());
    }

}
