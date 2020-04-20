package du.kakaocrop.coupon.api.service;

import java.util.List;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.constant.StoredProcedureStatus;
import du.kakaocrop.coupon.api.dao.CouponCodeDao;
import du.kakaocrop.coupon.api.dao.CouponDao;
import du.kakaocrop.coupon.api.dao.CouponUserDao;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.UserCouponInfo;
import du.kakaocrop.coupon.api.domain.error.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CouponServiceImpl implements CouponService {
    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);
    private final CouponDao couponDao;
    private final CouponUserDao couponUserDao;
    private final CouponCodeDao couponCodeDao;

    public CouponServiceImpl(CouponDao couponDao, CouponUserDao couponUserDao, CouponCodeDao couponCodeDao) {
        this.couponDao = couponDao;
        this.couponUserDao = couponUserDao;
        this.couponCodeDao = couponCodeDao;
    }

    @Override
    public CouponCode provideCoupon(Long userSeq, Long couponSeq) {
        // select member Key.
        // 쿠폰 한개를 가져온다.
        Integer code = couponDao.provideCoupon(userSeq, couponSeq);
        if (code == null || code < 0) {
            switch (StoredProcedureStatus.getRes(code)) {
                case ALREADY_PROVIDED:
                    // 이미 쿠폰이 발급되었을때,
                    throw new ApiException(ErrorType.ALREADY_PROVIDED_COUPON);
                case COUPON_EXHAUSTED:
                    // 발급할 쿠폰이 없을때.
                    throw new ApiException(ErrorType.COUPON_EXHAUSTED);
                case FAIL:
                case UNKNOWN:
                    logger.error("Coupon issuance failed");
                    throw new ApiException(ErrorType.UNKNOWN_ERROR);
            }
        }

        UserCouponInfo userCouponInfo = couponUserDao.getUserCouponStatus(userSeq, couponSeq);
        CouponCode couponCode = couponCodeDao.getCouponCode(couponSeq, userCouponInfo.getCouponCodeSeq());
        return couponCode;
    }


    @Override
    public List<UserCouponInfo> getUserCouponList(Long userSeq) {
        List<UserCouponInfo> userCouponList = couponUserDao.getUserCouponList(userSeq);
        return userCouponList;
    }

    @Override
    @Transactional
    public void useCoupon(Long userSeq, Long couponSeq) {
        //1. user의 쿠폰을 가져온다.
        UserCouponInfo info = couponUserDao.getUserCouponStatus(userSeq, couponSeq);
        if (info == null) {
            throw new ApiException(ErrorType.NO_COUPON);
        }

        //2. user의 쿠폰 사용 여부에 따라 업데이트를 실행한다.
        if (!couponUserDao.processCoupon(userSeq, couponSeq)) {
            throw new ApiException(ErrorType.CANNOT_USE_COUPON);
        }

        //3. Coupon의 사용 상태를 업데이트 한다.
        if (!couponCodeDao.processCouponCode(couponSeq, info.getCouponCodeSeq())) {
            throw new ApiException(ErrorType.CANNOT_USE_COUPON);
        }

    }

    @Override
    @Transactional
    public void cancelUseCoupon(Long userSeq, Long couponSeq) {
        //1. user의 쿠폰을 가져온다.
        UserCouponInfo info = couponUserDao.getUserCouponStatus(userSeq, couponSeq);
        if (info == null) {
            throw new ApiException(ErrorType.NO_COUPON);
        }

        //2. user의 쿠폰 사용 여부에 따라 업데이트를 실행한다.
        if (!couponUserDao.cancelUseCoupon(userSeq, couponSeq)) {
            throw new ApiException(ErrorType.CANNOT_CANCEL_COUPON);
        }

        //3. Coupon 의 사용 상태를 업데이트 한다.
        if (!couponCodeDao.cancelUseCouponCode(couponSeq, info.getCouponCodeSeq())) {
            throw new ApiException(ErrorType.CANNOT_CANCEL_COUPON);
        }
    }
}
