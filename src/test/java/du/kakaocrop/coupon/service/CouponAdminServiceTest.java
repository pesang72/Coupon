package du.kakaocrop.coupon.service;


import java.time.ZoneId;
import java.util.List;

import du.kakaocrop.coupon.api.dao.CouponCodeDao;
import du.kakaocrop.coupon.api.dao.test.TestDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.dto.req.RequestCouponDto;
import du.kakaocrop.coupon.api.service.CouponManageService;
import du.kakaocrop.coupon.bootstrap.CouponApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponApplication.class)
public class CouponAdminServiceTest {

    @Autowired
    CouponManageService couponManageService;

    @Autowired
    CouponCodeDao couponCodeDao;

    @Autowired
    TestDao testDao;

    @Test
    public void createCoupon(){
        Date date = new Date();
        String title = "Test_title"+date.getTime();
        String thumbnail = "thumbnail"+date.getTime();
        RequestCouponDto req = init(title,thumbnail);

        CouponInfo couponInfo = couponManageService.registerCoupon(new CouponInfo(req));

        Assert.assertEquals(couponInfo.getTitle() , title);
        Assert.assertEquals(couponInfo.getThumbnail() , thumbnail);
        Assert.assertEquals(couponInfo.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), 948272086000l);

        testDao.dropTestDataAndTable(1l,couponInfo.getCouponSeq());
    }

    @Test
    public void createCouponCode(){
        Date date = new Date();
        String title = "Test_title"+date.getTime();
        String thumbnail = "thumbnail"+date.getTime();
        RequestCouponDto req = init(title,thumbnail);

        Long createCount = 20l;

        CouponInfo couponInfo = couponManageService.registerCoupon(new CouponInfo(req));
        couponManageService.createCoupon(couponInfo.getCouponSeq(),createCount);
        Long count = testDao.couponCodeCount(couponInfo.getCouponSeq());

        Assert.assertEquals(createCount ,count);


        testDao.dropTestDataAndTable(1l,couponInfo.getCouponSeq());
    }

    private RequestCouponDto init(String title,String thumbnail){
        RequestCouponDto reqCouponInfo = new RequestCouponDto();
        Date date = new Date();
        reqCouponInfo.setTitle(title);
        reqCouponInfo.setThumbnail(thumbnail);
        reqCouponInfo.setEndDate(948272086000l);
        reqCouponInfo.setStartDate(948272086000l);
        return reqCouponInfo;
    }
}
