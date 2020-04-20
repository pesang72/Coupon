package du.kakaocrop.coupon.api.domain;

import java.time.LocalDateTime;

public class UserCouponInfo {
    private Long userSeq;
    private Long couponSeq;
    private Long couponCodeSeq;
    private Boolean isUsed;
    private LocalDateTime regDate;

    private String title;
    private String thumbnail;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getCouponSeq() {
        return couponSeq;
    }

    public void setCouponSeq(Long couponSeq) {
        this.couponSeq = couponSeq;
    }

    public Long getCouponCodeSeq() {
        return couponCodeSeq;
    }

    public void setCouponCodeSeq(Long couponCodeSeq) {
        this.couponCodeSeq = couponCodeSeq;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
