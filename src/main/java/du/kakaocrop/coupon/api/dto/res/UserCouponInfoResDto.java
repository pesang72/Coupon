package du.kakaocrop.coupon.api.dto.res;

import du.kakaocrop.coupon.api.domain.UserCouponInfo;

import java.time.ZoneId;

public class UserCouponInfoResDto {
    private Long couponSeq;
    private String title;
    private String thumbnail;
    private Long startDate;
    private Long endDate;
    private Boolean isUsed;

    public UserCouponInfoResDto(UserCouponInfo info) {
        this.couponSeq = info.getCouponSeq();
        this.title = info.getTitle();
        this.thumbnail = info.getThumbnail();
        this.startDate = info.getStartDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.endDate = info.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.isUsed = info.getUsed();
    }


    public Long getCouponSeq() {
        return couponSeq;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Long getStartDate() {
        return startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }


}
