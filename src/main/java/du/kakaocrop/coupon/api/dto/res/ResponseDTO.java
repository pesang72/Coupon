package du.kakaocrop.coupon.api.dto.res;

import du.kakaocrop.coupon.api.constant.ErrorType;

public class ResponseDTO<T> {
    private Integer code;
    private String message;
    private T result;

    public ResponseDTO(){
        this.code = ErrorType.SUCCESS.getErrorCode();
        this.message = ErrorType.SUCCESS.getErrorMsg();
    }

    public ResponseDTO(T result) {
        this.code = ErrorType.SUCCESS.getErrorCode();
        this.message = ErrorType.SUCCESS.getErrorMsg();
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
