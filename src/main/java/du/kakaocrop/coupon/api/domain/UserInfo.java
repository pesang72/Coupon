package du.kakaocrop.coupon.api.domain;

public class UserInfo {
    private Long userSeq;
    private String email;
    private String password;

    public UserInfo() {}

    /* something...*/

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
