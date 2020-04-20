package du.kakaocrop.coupon.api.domain;

import org.springframework.batch.item.ItemProcessor;

public class CouponCode {
    private Integer seq;
    private Long couponSeq;
    private String couponCode;
    private String targetUserId;
    private Boolean isUsed;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getCouponSeq() {
        return couponSeq;
    }

    public void setCouponSeq(Long couponSeq) {
        this.couponSeq = couponSeq;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
