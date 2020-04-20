package du.kakaocrop.coupon.api.dto;

import du.kakaocrop.coupon.api.constant.ErrorType;

public class BaseResDto {
    private Integer errorCode;
    private String errorMsg;
    private String causeMsg;

    public BaseResDto() {
        this.errorCode = ErrorType.SUCCESS.getErrorCode();
        this.errorMsg = ErrorType.SUCCESS.getErrorMsg();
    }

    public BaseResDto(Integer errorCode, String errorMsg, String causeMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.causeMsg = causeMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCauseMsg() {
        return causeMsg;
    }

    public void setCauseMsg(String causeMsg) {
        this.causeMsg = causeMsg;
    }
}
