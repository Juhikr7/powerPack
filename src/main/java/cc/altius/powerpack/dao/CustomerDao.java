package cc.altius.powerpack.dao;

import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.IdAndLabel;

import java.util.List;

public interface CustomerDao {
    public int addCustomer(Customer customer);
    public List<Customer> getCustomerListLast15();
    public List<Customer> findCustomer(String searchString);
}
