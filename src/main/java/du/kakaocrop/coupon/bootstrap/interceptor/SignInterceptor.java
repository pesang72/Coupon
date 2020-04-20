package du.kakaocrop.coupon.bootstrap.interceptor;

import du.kakaocrop.coupon.api.constant.Attributes;
import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.constant.SignKey;
import du.kakaocrop.coupon.api.domain.UserInfo;
import du.kakaocrop.coupon.api.domain.error.UnauthorizedException;
import du.kakaocrop.coupon.api.service.JwtService;
import du.kakaocrop.coupon.api.service.SignService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class SignInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    private final SignService signService;

    public SignInterceptor(JwtService jwtService, SignService signService) {
        this.jwtService = jwtService;
        this.signService = signService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String token = request.getHeader(SignKey.Authorization);

        if (token != null && jwtService.isUsable(token)) {
            Map<String, Object> userInfoMap = (Map<String, Object>) jwtService.get(token, SignKey.UserKey);
            if (userInfoMap == null) {
                throw new UnauthorizedException(ErrorType.INVALID_TOKEN.getErrorMsg());
            }

            UserInfo info = signService.getUserInfo(Long.parseLong(userInfoMap.get("userSeq").toString()));
            if(info == null){
                throw new UnauthorizedException();
            }
            request.setAttribute(Attributes.USER_INFO.name(), info);
            return true;
        } else {
            throw new UnauthorizedException(ErrorType.EXPIRED_TOKEN.getErrorMsg());
        }
    }
}
