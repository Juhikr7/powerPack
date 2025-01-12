package cc.altius.powerpack.web.controller;

import cc.altius.powerpack.model.IdAndLabel;
import cc.altius.powerpack.model.User;
import cc.altius.powerpack.propertyEditer.IdAndLabelPropertyEditor;
import cc.altius.powerpack.service.MasterService;
import cc.altius.powerpack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private MasterService masterService;
    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(IdAndLabel.class, new IdAndLabelPropertyEditor());
    }

    @RequestMapping(value = "addUser.htm", method = RequestMethod.GET)
    public String showAddUser(ModelMap model) {
        model.addAttribute("roleList", this.masterService.getRoleList());
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, ModelMap model) {
        try {
            String msg = this.userService.addUser(user);
            return "redirect:listUser.htm?msg="+msg;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("roleList", this.masterService.getRoleList());
            model.addAttribute("user", user);
            model.addAttribute("msg", "User could not be added - " + e.getMessage());
            return "addUser";
        }
    }

    @RequestMapping(value = "listUser.htm", method = RequestMethod.GET)
    public String listUser(ModelMap model) {
        model.addAttribute("userList", this.userService.getUserList());
        return "listUser";
    }

    @RequestMapping(value = "showEditUser.htm", method = RequestMethod.POST)
    public String showEditUser(@RequestParam(value = "userId", required = true) int userId, ModelMap model) {
        model.addAttribute("user", this.userService.getUserByUserId(userId));
        model.addAttribute("roleList", this.masterService.getRoleList());
        return "editUser";
    }

    @RequestMapping(value = "editUser.htm", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user, ModelMap model) {
        try {
            int rows = this.userService.editUser(user);
            if (rows == 0) {
                return "redirect:listUser.htm?msg=Nothing to update";
            } else {
                return "redirect:listUser.htm?msg=User successfully updated";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            model.addAttribute("roleList", this.masterService.getRoleList());
            model.addAttribute("msg", "User could not be updated - " + e.getMessage());
            return "editUser";
        }
    }

}
