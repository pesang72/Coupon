package du.kakaocrop.coupon.api.schedule;

import du.kakaocrop.coupon.api.domain.CouponCode;

import java.util.LinkedList;
import java.util.Queue;

public class AlarmQueue {
    public static Queue<CouponCode> queue = new LinkedList<>();

    private AlarmQueue(){}


    public static CouponCode pop(){
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll();
    }

    public static void addCoupon(CouponCode couponCode){
        queue.add(couponCode);
    }
}
