package du.kakaocrop.coupon.api.service;


import du.kakaocrop.coupon.api.dao.CouponCodeDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.schedule.AlarmQueue;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AlarmServiceImpl implements AlarmService{
    private static final Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

    private final CouponCodeDao couponCodeDao;

    public AlarmServiceImpl(CouponCodeDao couponCodeDao) {
        this.couponCodeDao = couponCodeDao;
    }

    @Override
    @Async("asyncThreadTaskExecutor")
    public void getCouponCodes(Long couponSeq) {
        couponCodeDao.getCouponCodeList(couponSeq,new ResultHandler<CouponCode>(){
            @Override
            public void handleResult(ResultContext<? extends CouponCode> resultContext) {
                // list의 리턴 결과를 해당 부분을 통해 받게되며, 알람을 전송할 유저값이 있는 경우 queue에 추가한다.
                CouponCode couponCode = resultContext.getResultObject();
                if(!StringUtils.isEmpty(couponCode.getTargetUserId()) && !couponCode.getUsed()){
                    AlarmQueue.addCoupon(couponCode);
                    logger.info("CouponCode, targetUser {} : {}", couponCode.getCouponCode(),couponCode.getTargetUserId());
                }
            }
        });
    }

    @Override
    @Async("asyncThreadTaskExecutor")
    public void sendAlarm(CouponCode couponCode) {
        // 전송부분 대체
        logger.info("targetUserSeq : {}, CouponCode : {} expire coupon alarm send!", couponCode.getTargetUserId(), couponCode.getCouponCode());
    }
}
