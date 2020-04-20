package du.kakaocrop.coupon.api.util;

import java.util.List;
import du.kakaocrop.coupon.api.domain.CouponCode;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class CouponSupportUtil {
    private static final Logger logger = LoggerFactory.getLogger(CouponSupportUtil.class);
    private static final int insertLimit = 500;

    /**
     * couponCount만큼의 쿠폰코드를 생성하여 set에 저장.
     * @param prefix 쿠폰 앞자리(5글자)
     * @param middle 쿠폰 중간(6글자)
     * @param textLength
     * @param couponCount
     * @return
     */
    public Set<String> couponSet(String prefix, String middle, Integer textLength, Long couponCount) {
        Set<String> couponSet = new HashSet<String>();

        int misSize = 0;
        while (couponSet.size() < couponCount) {
            String couponText = couponText(prefix, middle, createRandomText(textLength));
            if (!couponSet.contains(couponText)) {
                logger.debug("couponText : {} ", couponText);
                couponSet.add(couponText);
            } else {
                misSize++;
            }
        }

        logger.debug("couponText : {} ", misSize);
        return couponSet;
    }

    public String couponText(String prefix, String middle, String leaf) {
        StringBuilder tempCouponCode = new StringBuilder();
        tempCouponCode.append(prefix);
        tempCouponCode.append(middle);
        tempCouponCode.append(leaf);
        return tempCouponCode.toString();
    }


    /**
     * textLength만큼 랜덤한 string 생성
     * @param textLength
     * @return
     */
    public String createRandomText(Integer textLength) {
        StringBuilder tempRandomCode = new StringBuilder();
        tempRandomCode.delete(0, tempRandomCode.length());

        while (tempRandomCode.length() < textLength) {
            String sv = RandomStringUtils.random(24, true, true);
            tempRandomCode.append(sv.toUpperCase());
            tempRandomCode.reverse();
        }
        //logger.debug("random coupon : {} ", random.substring(0, textLength));
        return tempRandomCode.substring(0, textLength);
    }

    /**
     * inner를 통해 set에 저장되어있는 CouponCode를 가져오며 500개 단위로 끊어서 리턴.
     * 500개 이하일경우 전체 리턴
     * @param inner
     * @param couponSeq
     * @return
     */
    public List<CouponCode> getCouponToList(Iterator<String> inner, long couponSeq){
        ArrayList<CouponCode> list = new ArrayList<>();

        int loopCount = 0;
        while(inner.hasNext()){
            String couponCode = inner.next();

            CouponCode cd = new CouponCode();
            cd.setCouponSeq(couponSeq);
            cd.setCouponCode(couponCode);
            list.add(cd);
            loopCount++;

            if(insertLimit == loopCount)
                break;
        }
        return list;
    }

}
