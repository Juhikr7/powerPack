package cc.altius.powerpack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer implements Serializable {
    private int customerId;
    private String customerName;
    private String mobile;
    private String emailId;
    private int age;
    private IdAndLabel gender;
    private String orderId;
    private IdAndLabel createdBy;
    private String orderDate;
    private String orderTime;
}
