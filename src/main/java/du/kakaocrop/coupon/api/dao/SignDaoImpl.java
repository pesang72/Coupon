package du.kakaocrop.coupon.api.dao;

import du.kakaocrop.coupon.api.dao.mapper.SignMapper;
import du.kakaocrop.coupon.api.domain.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDaoImpl implements SignDao {
    private SignMapper signMapper;

    public SignDaoImpl(SignMapper signMapper) {
        this.signMapper = signMapper;
    }

    @Override
    public UserInfo findUserInfo(String email) {
        return signMapper.getUserByEmail(email);
    }

    @Override
    public UserInfo findUserInfoBySeq(Long userSeq) {
        return signMapper.getUserBySeq(userSeq);
    }
}
