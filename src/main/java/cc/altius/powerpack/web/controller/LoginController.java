package cc.altius.powerpack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
@Controller
public class LoginController {
    @RequestMapping("/")
    public String showRoot() {
        return "redirect:/index.htm";
    }

    @RequestMapping("/login.htm")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/index.htm")
    public String showIndex(){
        return "index";
    }
}
