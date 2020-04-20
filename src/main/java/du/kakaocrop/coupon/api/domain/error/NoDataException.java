package du.kakaocrop.coupon.api.domain.error;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.CommonException;

public class NoDataException extends CommonException {
    public NoDataException(){
        super(ErrorType.NO_DATA);
    }

    public NoDataException(String causeMsg){
        super(ErrorType.NO_DATA,causeMsg);
    }
}
