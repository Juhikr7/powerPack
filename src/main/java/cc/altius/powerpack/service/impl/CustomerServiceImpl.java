package cc.altius.powerpack.service.impl;

import cc.altius.powerpack.dao.CustomerDao;
import cc.altius.powerpack.dao.MasterDao;
import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.Item;
import cc.altius.powerpack.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private MasterDao masterDao;

    @Override
    public int addCustomer(Customer customer , String selectedIds) {
        String orderId = "";
        List <Item> itemList = masterDao.getAllItemList();
        Map<Integer,String> idCodeMap = itemList.stream().collect(Collectors.toMap(Item::getItemId,Item::getCode));
        for(String itemIdStr : selectedIds.split("-")){
            System.out.println("itemIdStr "+idCodeMap.get(Integer.parseInt(itemIdStr)));
            orderId += idCodeMap.get(Integer.parseInt(itemIdStr)) + "-";
        }
//        System.out.println(orderId.substring(0, orderId.length() - 1));
        customer.setOrderId(orderId.substring(0, orderId.length() - 1));
        return customerDao.addCustomer(customer);
    }

    @Override
    public List<Customer> getCustomerListLast15() {
        return customerDao.getCustomerListLast15();
    }

    @Override
    public List<Customer> findCustomer(String searchString) {
        return customerDao.findCustomer(searchString);
    }
}
