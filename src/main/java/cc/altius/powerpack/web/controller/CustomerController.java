package cc.altius.powerpack.web.controller;

import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.propertyEditer.IdAndLabelPropertyEditor;
import cc.altius.powerpack.service.CustomerService;
import cc.altius.powerpack.service.MasterService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private CustomerService customerService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(IdAndLabel.class, new IdAndLabelPropertyEditor());
    }

    @RequestMapping(value = "showCustomerRegPage.htm", method = RequestMethod.GET)
    public String showCustomerRegPage(ModelMap model,@RequestParam(value = "msg",required = false,defaultValue = "")String msg) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("genderList", this.masterService.getGenderList());
        model.addAttribute("msg", msg);
        return "customerReg";
    }

    @RequestMapping(value = "addItems.htm", method = RequestMethod.POST)
    public String addItems(@ModelAttribute("customer") Customer customer, ModelMap model) {
        try {
            model.addAttribute("customer",customer);
            Gson gson = new Gson();
            String mapLevelItemJson = gson.toJson(masterService.getLevelItemListMap());
            String mapIdNextLvlJson = gson.toJson(masterService.getIdNextLevelMap());
            model.addAttribute("mapLevelItemJson",mapLevelItemJson);
            model.addAttribute("mapIdNextLvlJson",mapIdNextLvlJson);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "addItems";
    }
    @RequestMapping(value = "saveOrderDetails.htm", method = RequestMethod.POST)
    public String saveOrderDetails(@ModelAttribute("customer") Customer customer,@RequestParam ("")String selectedIds, ModelMap model) {
        try {
            customerService.addCustomer(customer,selectedIds);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:showCustomerRegPage.htm?msg=Something Went Wrong Please Try Again !!";
        }
        return "redirect:listOrder.htm?msg=Order Placed successfully !";
    }

    @RequestMapping(value = "listOrder.htm", method = RequestMethod.GET)
    public String searchCustomer(@RequestParam(value = "searchString", required = false, defaultValue = "") String searchString,@RequestParam(value = "msg",required = false)String msg, ModelMap model) {
//        System.out.println("searchString=" + searchString);
        model.addAttribute("msg",msg);
        if (!searchString.isBlank()) {
            model.addAttribute("searchString", searchString);
            model.addAttribute("customerList",customerService.findCustomer(searchString));
        }else {
            model.addAttribute("customerList",customerService.getCustomerListLast15());
        }
        return "listOrder";
    }

}
