package du.kakaocrop.coupon.api.schedule;

import java.io.IOException;
import java.util.List;

import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.service.AlarmService;
import du.kakaocrop.coupon.api.service.CouponManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlarmTask {
    private static final Logger logger = LoggerFactory.getLogger(AlarmTask.class);

    public final AlarmService alarmService;
    public final CouponManageService couponManageService;

    public AlarmTask(AlarmService alarmService, CouponManageService couponManageService) {
        this.alarmService = alarmService;
        this.couponManageService = couponManageService;
    }

    @Scheduled(cron = "* */5 * * * *")
    public void process() throws IOException {
        logger.debug("start check coupon endDate");
        List<CouponInfo> coupons = couponManageService.getExpiredCoupon(3, 5, 3, 0);
        for (CouponInfo coupon : coupons) {
            logger.debug("find couponSeq : {}",coupon.getCouponSeq());
            alarmService.getCouponCodes(coupon.getCouponSeq());
        }
    }


    @Scheduled(cron = "*/1 * * * * *")
    public void queueProcess() {
        logger.debug("queue check!!");
        CouponCode code;
        while ((code = AlarmQueue.pop()) != null) {
            alarmService.sendAlarm(code);
        }
    }
}
