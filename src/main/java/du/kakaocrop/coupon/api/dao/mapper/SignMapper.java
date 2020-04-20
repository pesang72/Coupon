package du.kakaocrop.coupon.api.dao.mapper;

import du.kakaocrop.coupon.api.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignMapper {
    public UserInfo getUserByEmail(@Param("email")String email);

    public UserInfo getUserBySeq(@Param("userSeq") Long userSeq);
}
