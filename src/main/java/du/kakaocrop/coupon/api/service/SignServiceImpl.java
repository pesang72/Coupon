package du.kakaocrop.coupon.api.service;

import du.kakaocrop.coupon.api.dao.SignDao;
import du.kakaocrop.coupon.api.domain.UserInfo;
import du.kakaocrop.coupon.api.domain.error.UnauthorizedException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    private SignDao signDao;

    public SignServiceImpl(SignDao signDao) {
        this.signDao = signDao;
    }

    public UserInfo signIn(String email, String password) {
        UserInfo userInfo = signDao.findUserInfo(email);

        if(userInfo == null){
            throw new UnauthorizedException("유저 정보가 존재하지 않습니다.");
        }

        if( !this.isAccordPassword(userInfo, password)){
            throw new UnauthorizedException("올바르지 않은 계정정보입니다.");
        }

        return userInfo;
    }

    @Override
    public UserInfo getUserInfo(Long userSeq) {
        if(userSeq == null)
            return null;
        return signDao.findUserInfoBySeq(userSeq);
    }

    private boolean isAccordPassword(UserInfo userInfo, String password){
        String encodedPassword = userInfo.getPassword();
        return BCrypt.checkpw(password, encodedPassword);
    }

}
