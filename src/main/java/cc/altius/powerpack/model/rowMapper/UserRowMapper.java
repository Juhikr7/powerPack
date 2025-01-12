package cc.altius.powerpack.model.rowMapper;

import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User(
                rs.getInt("USER_ID"),
                rs.getString("NAME"),
                rs.getString("USER_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                new IdAndLabel(rs.getString("ROLE_ID"), rs.getString("ROLE_DESC")),
                null,
                rs.getBoolean("ACTIVE")
        );

        String businessFunctionIds = rs.getString("BUSINESS_FUNCTION_IDS");
        List<GrantedAuthority> businessFunctionList = new LinkedList<>();
        for (String businessFunctionId : businessFunctionIds.split(",")) {
            businessFunctionList.add(new SimpleGrantedAuthority(businessFunctionId));
        }
        u.setBusinessFunctionList(businessFunctionList);
        return u;
    }

}
