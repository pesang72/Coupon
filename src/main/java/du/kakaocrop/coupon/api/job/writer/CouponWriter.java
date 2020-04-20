package du.kakaocrop.coupon.api.job.writer;

import java.util.List;

import du.kakaocrop.coupon.api.dao.CouponDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.service.CouponServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class CouponWriter<T> implements ItemWriter<T> {
    private static final Logger logger = LoggerFactory.getLogger(CouponWriter.class);
    private CouponDao couponDao;

    public CouponWriter(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    @Override
    public void write(List<? extends T> list) throws Exception {
        // csv 파일을 읽어온 후 후처리 작업진행

        for(Object obj : list){
            if(obj instanceof CouponCode){
                logger.info("CouponCode : {}",((CouponCode) obj).getCouponCode());
            }
        }
    }
}