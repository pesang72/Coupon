package du.kakaocrop.coupon.service;

import java.util.List;
import du.kakaocrop.coupon.api.dao.CouponCodeDao;
import du.kakaocrop.coupon.api.dao.test.TestDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import du.kakaocrop.coupon.api.dto.req.RequestCouponDto;
import du.kakaocrop.coupon.api.service.CouponManageService;
import du.kakaocrop.coupon.api.service.CouponService;
import du.kakaocrop.coupon.bootstrap.CouponApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponApplication.class)
public class CouponServiceTest {

    @Autowired
    CouponManageService couponManageService;

    @Autowired
    CouponService couponService;

    @Autowired
    TestDao testDao;

    @Test
    public void couponUserTest(){
        Date date = new Date();
        String title = "Test_title"+date.getTime();
        String thumbnail = "thumbnail"+date.getTime();
        RequestCouponDto req = init(title,thumbnail);
        CouponInfo couponInfo = couponManageService.registerCoupon(new CouponInfo(req));
        couponManageService.createCoupon(couponInfo.getCouponSeq(),20l);
        Long userSeq = -1l;


        CouponCode providedCoupon = couponService.provideCoupon(userSeq, couponInfo.getCouponSeq());
        List<UserCouponInfo> list = couponService.getUserCouponList(userSeq);

        //Assert.assertEquals(list.size(),1);
        Assert.assertEquals(list.get(list.size()-1).getCouponSeq(),couponInfo.getCouponSeq());
        Assert.assertEquals(list.get(list.size()-1).getUsed(),false);

        // 쿠폰 사용 후, 비교
        couponService.useCoupon(userSeq,couponInfo.getCouponSeq());
        list = couponService.getUserCouponList(userSeq);
        Assert.assertEquals(list.get(list.size()-1).getUsed(),true);

        // 쿠폰 사용 취소 후 비교
        couponService.cancelUseCoupon(userSeq, couponInfo.getCouponSeq());
        list = couponService.getUserCouponList(userSeq);
        Assert.assertEquals(list.get(list.size()-1).getUsed(),false);


        testDao.dropTestDataAndTable(-1l,couponInfo.getCouponSeq());
    }


    private RequestCouponDto init(String title,String thumbnail){
        RequestCouponDto reqCouponInfo = new RequestCouponDto();
        reqCouponInfo.setTitle(title);
        reqCouponInfo.setThumbnail(thumbnail);
        reqCouponInfo.setEndDate(1587287569000l);
        reqCouponInfo.setStartDate(1587287569000l);
        return reqCouponInfo;
    }
}
