package du.kakaocrop.coupon.api.domain;

import du.kakaocrop.coupon.api.constant.ErrorCodable;
import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.dto.BaseResDto;

public class CommonException extends RuntimeException {
    private ErrorCodable errorCode;
    private String causeMsg;

    public CommonException() {
        this.errorCode = ErrorType.SUCCESS;
        this.causeMsg = "";
    }

    public CommonException(ErrorCodable errorCode) {
        this.errorCode = errorCode;
        this.causeMsg = "";
    }

    public CommonException(ErrorCodable errorCode, String causeMsg) {
        this.errorCode = errorCode;
        this.causeMsg = causeMsg;
    }

    public ErrorCodable getErrorCode() {
        return errorCode;
    }

    public String getCauseMsg() {
        return causeMsg;
    }

    public ErrorResponse createErrorResponse() {
        return new ErrorResponse(errorCode.getErrorCode(), errorCode.getErrorMsg(), causeMsg);
    }

    public static class ErrorResponse extends BaseResDto {
        public ErrorResponse(Integer errorCode, String errorMsg, String causeMsg) {
            setErrorCode(errorCode);
            setErrorMsg(errorMsg);
            setCauseMsg(causeMsg);
        }
    }
}
