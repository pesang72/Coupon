package du.kakaocrop.coupon.util;

import du.kakaocrop.coupon.api.util.CouponSupportUtil;
import du.kakaocrop.coupon.bootstrap.CouponApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CouponApplication.class)
public class StringUtilTest {

    @Autowired
    CouponSupportUtil couponSupportUtil;

    @Test
    public void randomTextTest() {
        String random1 = couponSupportUtil.createRandomText(5);
        Assert.assertEquals(random1.length(), 5);

        String random2 = couponSupportUtil.createRandomText(9);
        Assert.assertEquals(random2.length(), 9);

        String random3 = couponSupportUtil.createRandomText(15);
        Assert.assertEquals(random3.length(), 15);
    }

    @Test
    public void makeCouponSeqTest() {
        int root = 5;
        int middle = 5;
        int leaf = 5;
        long couponCount = 500;

        Set<String> couponSet = couponSupportUtil.couponSet(couponSupportUtil.createRandomText(root), couponSupportUtil.createRandomText(middle), leaf, couponCount);
        Assert.assertEquals(couponSet.size(), 500);
        for (String st : couponSet) {
            Assert.assertEquals(st.length(), root + middle + leaf);
        }
    }

    @Test
    public void setToListTest() {
        int root = 5;
        int middle = 5;
        int leaf = 5;
        long couponCount = 505;

        Set<String> couponSet = couponSupportUtil.couponSet(couponSupportUtil.createRandomText(root), couponSupportUtil.createRandomText(middle), leaf, couponCount);
        Iterator<String> inner = couponSet.iterator();

        Assert.assertEquals(couponSupportUtil.getCouponToList(inner, 1l).size(), 500);
        Assert.assertEquals(couponSupportUtil.getCouponToList(inner, 1l).size(), 5);

        long couponCount2 = 1511;
        Set<String> couponSet2 = couponSupportUtil.couponSet(couponSupportUtil.createRandomText(root), couponSupportUtil.createRandomText(middle), leaf, couponCount2);
        Iterator<String> inner2 = couponSet2.iterator();

        Assert.assertEquals(couponSupportUtil.getCouponToList(inner2, 1l).size(), 500);
        Assert.assertEquals(couponSupportUtil.getCouponToList(inner2, 1l).size(), 500);
        Assert.assertEquals(couponSupportUtil.getCouponToList(inner2, 1l).size(), 500);
        Assert.assertEquals(couponSupportUtil.getCouponToList(inner2, 1l).size(), 11);

    }
}
