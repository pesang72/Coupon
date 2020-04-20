package du.kakaocrop.coupon.api.dao.test;

import du.kakaocrop.coupon.api.dao.mapper.test.TestMapper;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Repository
public class TestDaoImpl implements TestDao{

    private final TestMapper testMapper;

    public TestDaoImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    @Transactional
    public void dropTestDataAndTable(Long userSeq, Long couponSeq) {
        testMapper.dropCouponCodeTable(couponSeq);
        testMapper.dropCouponInfo(couponSeq);
        testMapper.dropPrefixData(couponSeq);
        testMapper.dropCountData(couponSeq);
        testMapper.dropUserData(userSeq, couponSeq);
    }

    @Override
    public Long couponCodeCount(Long couponSeq) {
        return testMapper.getCouponCodeRowCount(couponSeq);
    }
}
