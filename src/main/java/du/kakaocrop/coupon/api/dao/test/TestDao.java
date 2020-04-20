package du.kakaocrop.coupon.api.dao.test;

public interface TestDao {
    public void dropTestDataAndTable(Long userSeq, Long couponSeq);

    public Long couponCodeCount(Long couponSeq);
}
