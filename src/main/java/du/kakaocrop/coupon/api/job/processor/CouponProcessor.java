package du.kakaocrop.coupon.api.job.processor;

import du.kakaocrop.coupon.api.domain.CouponCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CouponProcessor implements ItemProcessor<CouponCode, CouponCode> {

    private static final Logger log = LoggerFactory.getLogger(CouponProcessor.class);

    @Override
    public CouponCode process(final CouponCode couponCode) throws Exception {
        log.info("Converting (" + couponCode + ") into (" + couponCode.getCouponCode() + ")");
        return couponCode;
    }

}