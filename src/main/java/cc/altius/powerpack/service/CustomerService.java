package cc.altius.powerpack.service;

import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.IdAndLabel;

import java.util.List;


public interface CustomerService {
    public int addCustomer(Customer customer, String selectedIds);
//    public List<IdAndLabel> getFirstListForDropDown();
    public List<Customer> getCustomerListLast15();
    public List<Customer> findCustomer(String searchString);
}
