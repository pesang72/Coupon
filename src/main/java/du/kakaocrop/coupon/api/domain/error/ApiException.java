package du.kakaocrop.coupon.api.domain.error;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.CommonException;

public class ApiException extends CommonException {

    public ApiException(ErrorType type){
        super(type);
    }

    public ApiException(ErrorType type,String causeMsg){
        super(type,causeMsg);
    }
}
