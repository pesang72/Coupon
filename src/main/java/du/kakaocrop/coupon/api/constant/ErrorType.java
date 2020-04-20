package du.kakaocrop.coupon.api.constant;

public enum ErrorType implements ErrorCodable {
    SUCCESS(0, "SUCCESS"),
    INVALID_VALUE(1000, "INVALID VALUE"),
    NO_DATA(2000, "NO DATA"),

    //쿠폰 관련
    NO_COUPON(3000,"쿠폰 발급 후 사용이 가능합니다."),
    CANNOT_USE_COUPON(3001,"발급 여부 및 사용여부를 확인해주세요"),
    CANNOT_CANCEL_COUPON(3002,"쿠폰 사용 여부를 확인해주세요"),
    ALREADY_PROVIDED_COUPON(3020,  "이미 쿠폰이 발급되었습니다."),
    COUPON_EXHAUSTED(3030,"쿠폰이 모두 소진되었습니다."),
    ALREADY_CREATED_COUPON(3100,"이미 쿠폰이 생성되었습니다."),

    //인증 코드
    UNAUTHORIZED(4000, "UNAUTHORIZED"),
    EXPIRED_TOKEN(4001, "인증 후 사용가능합니다."),
    INVALID_TOKEN(4002, "유효하지 않은 토큰입니다."),

    UNKNOWN_ERROR(9999, "UNKNOWN ERROR")
    ;

    private Integer errorCode;
    private String errorMsg;

    ErrorType(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

}

