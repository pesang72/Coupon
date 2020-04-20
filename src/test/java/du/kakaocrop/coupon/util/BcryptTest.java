package du.kakaocrop.coupon.util;


import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;


public class BcryptTest {

    @Test
    public void Test(){
        System.out.println(BCrypt.hashpw("1234", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("1234", BCrypt.gensalt()));
        System.out.println(BCrypt.hashpw("1234", BCrypt.gensalt()));
        System.out.println(BCrypt.checkpw("1234", BCrypt.hashpw("1234", BCrypt.gensalt())));
        System.out.println(BCrypt.checkpw("1234", BCrypt.hashpw("1234", BCrypt.gensalt())));
        System.out.println(BCrypt.checkpw("1234", BCrypt.hashpw("1234", BCrypt.gensalt())));
    }
}
