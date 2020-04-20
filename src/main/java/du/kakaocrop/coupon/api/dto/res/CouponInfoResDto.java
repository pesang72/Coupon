package du.kakaocrop.coupon.api.dto.res;

import du.kakaocrop.coupon.api.domain.CouponInfo;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CouponInfoResDto {
    private String title;
    private String thumbnail;
    private Long startDate;
    private Long endDate;

    public CouponInfoResDto(CouponInfo couponInfo){
        this.title = couponInfo.getTitle();
        this.thumbnail = couponInfo.getThumbnail();
        this.startDate = couponInfo.getStartDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.endDate = couponInfo.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
