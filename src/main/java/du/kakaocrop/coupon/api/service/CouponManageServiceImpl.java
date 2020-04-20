package du.kakaocrop.coupon.api.service;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.dao.CouponCodeDao;
import du.kakaocrop.coupon.api.dao.CouponDao;
import du.kakaocrop.coupon.api.dao.CouponPrefixDao;
import du.kakaocrop.coupon.api.domain.error.ApiException;
import du.kakaocrop.coupon.api.domain.error.InvalidParamException;
import du.kakaocrop.coupon.api.domain.error.NoDataException;
import du.kakaocrop.coupon.api.dto.BaseResDto;
import du.kakaocrop.coupon.api.domain.CouponCode;
import du.kakaocrop.coupon.api.domain.CouponInfo;
import du.kakaocrop.coupon.api.util.CouponSupportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;


@Service
public class CouponManageServiceImpl implements CouponManageService {
    private static final Logger logger = LoggerFactory.getLogger(CouponManageServiceImpl.class);
    private static final Long couponLoop = 100000l;

    private final CouponPrefixDao couponPrefixDao;
    private final CouponCodeDao couponCodeDao;
    private final CouponDao couponDao;
    private final CouponSupportUtil supportUtil;

    @Autowired
    public CouponManageServiceImpl(CouponPrefixDao couponPrefixDao, CouponCodeDao couponCodeDao, CouponDao couponDao, CouponSupportUtil supportUtil) {
        this.couponPrefixDao = couponPrefixDao;
        this.couponCodeDao = couponCodeDao;
        this.couponDao = couponDao;
        this.supportUtil = supportUtil;
    }


    /**
     * 쿠폰 생성 이전, 쿠폰을 등록작업 진행
     *
     * @param couponInfo
     * @return
     */
    @Override
    public CouponInfo registerCoupon(CouponInfo couponInfo) {
        boolean end = false;
        while (!end) {
            String prefix = supportUtil.createRandomText(5);
            if (couponDao.existPrefix(prefix) == 0) {
                couponInfo.setCouponPrefix(prefix);
                end = true;
            }
        }
        logger.debug("Coupon prefix is : {}",couponInfo.getCouponPrefix());

        Long code = couponDao.registerCoupon(couponInfo);
        if (code > 0) {
            CouponInfo resCouponInfo = couponDao.getCouponInfo(code);
            if (resCouponInfo == null) {
                throw new NoDataException("쿠폰 생성에 실패하였습니다.");
            }
            return resCouponInfo;
        }

        throw new ApiException(ErrorType.UNKNOWN_ERROR);
    }

    /**
     * 1. 쿠폰 생성 API.
     *
     * @param couponSeq   쿠폰 번호
     * @param createCount 생성할 쿠폰 개수
     * @return
     */
    public BaseResDto createCoupon(Long couponSeq, Long createCount) {

        CouponInfo info = couponDao.getCouponInfo(couponSeq);
        if(info.getService()){
            throw new ApiException(ErrorType.ALREADY_CREATED_COUPON);
        }

        String prefix = couponPrefixDao.getPrefix(couponSeq);
        Set<String> middleGroup = new HashSet<>();

        long remainCount = createCount;
        long loopCnt = createCount / couponLoop + (createCount % couponLoop > 0 ? 1 : 0);

        while (middleGroup.size() != loopCnt) {
            String middle = supportUtil.createRandomText(6);
            if (!middleGroup.contains(middle)) {
                middleGroup.add(middle);
            }
        }

        /**
         * 10만개 단위로 중간 단어6개를 변경해가며 생성
         */
        long totalCount = 0;
        Iterator<String> iter = middleGroup.iterator();
        while (iter.hasNext()) {
            String middle = iter.next();
            long workCount = remainCount - couponLoop > 0 ? couponLoop : remainCount;
            Set<String> couponSet = supportUtil.couponSet(prefix, middle, 8, workCount);

            /**
             * INSERT 작업 진행
             * 500개씩 끊어서 가져온 후 등록.
             */
            Iterator<String> inner = couponSet.iterator();
            List<CouponCode> codes = null;
            while (inner.hasNext() && (codes = supportUtil.getCouponToList(inner, couponSeq)).size() > 0) {
                int count = couponCodeDao.insertCouponCode(couponSeq, codes);
                totalCount += count;
            }

            logger.debug("count {} is done" ,totalCount);
            remainCount -= couponLoop;
        }

        if(createCount == totalCount){
            logger.debug("Coupon create Success");
        }


        couponDao.updateCouponCountAndStatus(couponSeq,createCount,"Y");
        return new BaseResDto();
    }

    /**
     * 6번 API 구현 : 당일 00:00 부터 현재시간 사이에 만료된 쿠폰 조회
     *
     * @return
     */
    @Override
    public List<CouponInfo> getExpiredCoupon() {
        List<CouponInfo> res = new ArrayList<>();

        Timestamp start_tmp = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp end_tmp = Timestamp.valueOf(LocalDateTime.now());
        List<CouponInfo> list = couponDao.getExpiredCoupon(start_tmp, end_tmp);

        for (CouponInfo info : list) {
            res.add(info);
        }
        return res;
    }

    /**
     * 7번 : 특정 시간 사이에 만료되는 쿠폰 조회
     *
     * @param fromDays
     * @param fromMinutes
     * @param toDays
     * @param toMinutes
     * @return
     */
    @Override
    public List<CouponInfo> getExpiredCoupon(Integer fromDays, Integer fromMinutes, Integer toDays, Integer toMinutes) {
        List<CouponInfo> res = new ArrayList<>();

        Timestamp startTime = Timestamp.valueOf(LocalDateTime.now().minusDays(fromDays).minusMinutes(fromMinutes));
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.now().minusDays(toDays).minusMinutes(toMinutes));
        List<CouponInfo> list = couponDao.getExpiredCoupon(startTime, endTime);

        for (CouponInfo info : list) {
            res.add(info);
        }
        return res;
    }

}
