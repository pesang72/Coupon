package du.kakaocrop.coupon.api.domain.error;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.CommonException;

public class InvalidParamException extends CommonException {

    public InvalidParamException(){
        super(ErrorType.INVALID_VALUE);
    }

    public InvalidParamException(String causeMsg){
        super(ErrorType.INVALID_VALUE,causeMsg);
    }
}
