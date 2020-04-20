package du.kakaocrop.coupon.api.domain.error;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.domain.CommonException;

public class UnauthorizedException extends CommonException {
    public UnauthorizedException() {
        super(ErrorType.UNAUTHORIZED);
    }

    public UnauthorizedException(String causeMsg) {
        super(ErrorType.UNAUTHORIZED, causeMsg);
    }
}