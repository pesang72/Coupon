package du.kakaocrop.coupon.api.domain;

import du.kakaocrop.coupon.api.dto.req.RequestCouponDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CouponInfo {
    private Long seq;
    private Long couponSeq;
    private String title;
    private String thumbnail;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime regDate;

    private String couponPrefix;
    private Boolean isService;


    public CouponInfo() {
    }

    public CouponInfo(RequestCouponDto reqCouponInfo) {
        this.title = reqCouponInfo.getTitle();
        this.thumbnail = reqCouponInfo.getThumbnail();
        this.startDate = millsToLocalDateTime(reqCouponInfo.getStartDate());
        this.endDate = millsToLocalDateTime(reqCouponInfo.getEndDate());
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getCouponSeq() {
        return couponSeq;
    }

    public void setCouponSeq(Long couponSeq) {
        this.couponSeq = couponSeq;
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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getCouponPrefix() {
        return couponPrefix;
    }

    public void setCouponPrefix(String couponPrefix) {
        this.couponPrefix = couponPrefix;
    }

    public Boolean getService() {
        return isService;
    }

    public void setService(Boolean service) {
        isService = service;
    }

    public static LocalDateTime millsToLocalDateTime(long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }
}
