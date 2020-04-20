package du.kakaocrop.coupon.api.service;

import du.kakaocrop.coupon.api.constant.ErrorType;
import du.kakaocrop.coupon.api.constant.SignKey;
import du.kakaocrop.coupon.api.domain.error.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
    private static final String SALT = "8GCT0G7DWT9R";

    @Override
    public <T> String create(String key, T data, String subject) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "KAKAOPAY_JWT")
                .setHeaderParam(SignKey.regTokenTime, System.currentTimeMillis())
                .setSubject(subject)
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(" JWT Key Error : {}", e.getMessage());
        }

        return key;
    }

    @Override
    public boolean isUsable(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(token);

            Long publicationTime = (Long) claims.getHeader().get(SignKey.regTokenTime);
            if(publicationTime ==null){
                throw new UnauthorizedException(ErrorType.INVALID_TOKEN.getErrorMsg());
            }

            // 30분 단위로 토큰인증을 만료처리한다.
            if (((new Date().getTime() - publicationTime) / 60000 > 30)) {
                return false;
            }

            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }
    }

    @Override
    public Object get(String token, String key) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(token);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }
        return claims.getBody().get(key);
    }
}
