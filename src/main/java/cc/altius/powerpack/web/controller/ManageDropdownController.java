package cc.altius.powerpack.web.controller;

import cc.altius.powerpack.model.Customer;
import cc.altius.powerpack.service.MasterService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageDropdownController {

    @Autowired
    private MasterService masterService;

    @RequestMapping(value = "manageFlow.htm", method = RequestMethod.GET)
    public String showManageDropdown(ModelMap model, @RequestParam(value = "msg",required = false,defaultValue = "")String msg) {
        Gson gson = new Gson();
        String mapLevelItemJson = gson.toJson(masterService.getLevelItemListMap());
        String mapIdNextLvlJson = gson.toJson(masterService.getIdNextLevelMap());
        model.addAttribute("mapLevelItemJson",mapLevelItemJson);
        model.addAttribute("mapIdNextLvlJson",mapIdNextLvlJson);
        model.addAttribute("msg", msg);
        return "manageFlow";
    }
}
