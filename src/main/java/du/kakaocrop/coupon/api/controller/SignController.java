package du.kakaocrop.coupon.api.controller;

import du.kakaocrop.coupon.api.constant.SignKey;
import du.kakaocrop.coupon.api.domain.UserInfo;
import du.kakaocrop.coupon.api.domain.error.InvalidParamException;
import du.kakaocrop.coupon.api.dto.BaseResDto;
import du.kakaocrop.coupon.api.service.JwtService;
import du.kakaocrop.coupon.api.service.SignService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/coupon/sign")
public class SignController {

    private JwtService jwtService;
    private SignService signService;
    public SignController(JwtService jwtService, SignService signService) {
        this.jwtService = jwtService;
        this.signService = signService;
    }

    // JWT 인증 코드 발급 부분.
    @PostMapping("")
    public BaseResDto signIn(String email, String password, HttpServletResponse response){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new InvalidParamException("데이터를 확인해주세요");
        }

        UserInfo userInfo = signService.signIn(email, password);
        String token = jwtService.create(SignKey.UserKey, userInfo, "userInfo");
        response.setHeader(SignKey.Authorization, token);

        return new BaseResDto();
    }
}
