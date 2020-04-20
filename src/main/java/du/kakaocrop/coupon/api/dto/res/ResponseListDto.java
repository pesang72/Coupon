package du.kakaocrop.coupon.api.dto.res;

import du.kakaocrop.coupon.api.constant.ErrorCodable;
import du.kakaocrop.coupon.api.constant.ErrorType;

import java.util.List;

public class ResponseListDto<T> {
    private Integer code;
    private String message;
    private List<T> results;

    public ResponseListDto( List<T> results) {
        this.code = ErrorType.SUCCESS.getErrorCode();
        this.message = ErrorType.SUCCESS.getErrorMsg();
        this.results = results;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getResults() {
        return results;
    }
}
