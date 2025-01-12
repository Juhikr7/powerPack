package cc.altius.powerpack.dao.impl;

import cc.altius.powerpack.dao.UserDao;
import cc.altius.powerpack.model.User;
import cc.altius.powerpack.model.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final String userSql = "SELECT u1.*FROM ( "+
            "SELECT u.USER_ID, "+
            "u.NAME,u.USER_NAME, "+
            "u.PASSWORD, "+
            "u.EMAIL, "+
            "u.ACTIVE, "+
            "r.ROLE_ID, "+
            "r.ROLE_DESC, "+
            "GROUP_CONCAT(rbf.BUSINESS_FUNCTION_ID) AS `BUSINESS_FUNCTION_IDS` "+
            "FROM usr_user u "+
            "LEFT JOIN usr_role r ON u.ROLE_ID = r.ROLE_ID "+
            "LEFT JOIN usr_role_business_function rbf ON r.ROLE_ID = rbf.ROLE_ID "+
            "GROUP BY u.USER_ID,u.NAME,u.USER_NAME,u.PASSWORD,u.ACTIVE,r.ROLE_ID,r.ROLE_DESC) AS u1 "+
            "WHERE TRUE ";

    @Override
    public List<User> getUserList() {
        return this.jdbcTemplate.query(this.userSql, new UserRowMapper());
    }

    @Override
    @Transactional
    public int addUser(User user) {
        SimpleJdbcInsert si = new SimpleJdbcInsert(dataSource).withTableName("usr_user").usingGeneratedKeyColumns("USER_ID");
        Map<String, Object> params = new HashMap<>();
        params.put("NAME", user.getName());
        params.put("USER_NAME", user.getUsername());
        params.put("EMAIL",user.getEmail());
        params.put("PASSWORD", user.getPassword());
        params.put("ROLE_ID", user.getRole().getId());
        params.put("ACTIVE", true);
        return si.executeAndReturnKey(params).intValue();
    }

    @Override
    public User getUserByUserId(int userId) {
        String sqlString = this.userSql + " AND u1.USER_ID=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new UserRowMapper());
    }

    @Override
    public int editUser(User user) {
        String sqlString = "UPDATE usr_user  "
                + "SET "
                + "NAME =:name, "
                + "EMAIL =:email ,"
                + "ROLE_ID =:roleId ,"
                + "ACTIVE =:active "
                + "WHERE USER_ID=:userId";
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("roleId", user.getRole().getId());
        params.put("userId", user.getUserId());
        params.put("active", user.isActive());
        params.put("email",user.getEmail());
        return this.namedParameterJdbcTemplate.update(sqlString, params);
    }

    @Override
    public User loadUserByUsername(String username) {
        String sqlString = this.userSql + " AND u1.USER_NAME=:username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return this.namedParameterJdbcTemplate.queryForObject(sqlString, params, new UserRowMapper());
    }
}
