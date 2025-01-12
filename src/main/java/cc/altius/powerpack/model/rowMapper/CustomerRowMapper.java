package cc.altius.powerpack.model.rowMapper;

import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.IdAndLabel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer(
                rs.getInt("CUSTOMER_ID"),
                rs.getString("CUSTOMER_NAME"),
                rs.getString("MOBILE"),
                rs.getString("EMAIL_ID"),
                rs.getInt("AGE"),
                new IdAndLabel(rs.getString("GENDER"), rs.getString("GENDER").equals("M") ? "Male" : "Female"),
                rs.getString("ORDER_ID"),
                new IdAndLabel(rs.getString("CREATED_BY_ID"),rs.getString("CREATED_BY_NAME")),
                null,
                null
        );
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        customer.setOrderDate(LocalDate.parse(rs.getString("ORDER_DATE"), inputFormatter).format(outputFormatter));
        customer.setOrderTime(LocalTime.parse(rs.getString("ORDER_TIME"), inputTimeFormatter).format(outputTimeFormatter));
        return customer;
    }
}
