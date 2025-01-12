package cc.altius.powerpack.dao.impl;

import cc.altius.powerpack.dao.CustomerDao;
import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.User;
import cc.altius.powerpack.model.rowMapper.CustomerRowMapper;
import cc.altius.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sqlString ="SELECT " +
            "c.CUSTOMER_ID, " +
            "c.CUSTOMER_NAME, " +
            "c.MOBILE, " +
            "c.EMAIL_ID, " +
            "c.AGE, " +
            "c.GENDER, " +
            "c.ORDER_ID, " +
            "c.CREATED_BY_ID, " +
            "u.NAME AS CREATED_BY_NAME, " +
            "c.ORDER_DATE, " +
            "c.ORDER_TIME " +
            "FROM pp_customer c " +
            "LEFT JOIN usr_user u " +
            "ON c.CREATED_BY_ID = u.USER_ID ";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public int addCustomer(Customer customer) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("pp_customer").usingGeneratedKeyColumns("CUSTOMER_ID");
        Map<String,Object> params = new HashMap<>();
        params.put("CUSTOMER_NAME", customer.getCustomerName());
        params.put("MOBILE", customer.getMobile());
        params.put("EMAIL_ID", customer.getEmailId());
        params.put("AGE", customer.getAge());
        params.put("GENDER", customer.getGender().getId());
        params.put("ORDER_ID",customer.getOrderId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        params.put("CREATED_BY_ID", user.getUserId());
        params.put("ORDER_DATE",DateUtils.getCurrentDateObject(DateUtils.IST));
        params.put("ORDER_TIME",LocalTime.now());
        return simpleJdbcInsert.executeAndReturnKey(params).intValue();
    }

    @Override
    public List<Customer> getCustomerListLast15() {
        String sql = sqlString + "ORDER BY " +
                "c.ORDER_DATE DESC, " +
                "c.ORDER_TIME DESC " +
                "LIMIT 15";
        return jdbcTemplate.query(sql,new CustomerRowMapper());
    }

    @Override
    public List<Customer> findCustomer(String searchString) {
        String sql = sqlString + "WHERE c.CUSTOMER_NAME LIKE CONCAT('%',:searchString,'%') " +
                "OR c.EMAIL_ID LIKE CONCAT('%',:searchString,'%') ORDER BY c.ORDER_DATE DESC, c.ORDER_TIME DESC";
//        System.out.println(sql);
        Map<String, Object> params = new HashMap<>();
        params.put("searchString", searchString);
        return namedParameterJdbcTemplate.query(sql,params,new CustomerRowMapper());
    }
}

