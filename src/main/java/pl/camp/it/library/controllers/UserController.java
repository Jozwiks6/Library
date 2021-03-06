package pl.camp.it.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model){
        model.addAttribute("user", new User());

        return "register";
    }

    @RequestMapping(value= "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2) {
        Pattern compiledPattern = Pattern.compile(".*[0-9]+.*");
        Matcher matcher = compiledPattern.matcher(user.getPassword());
        matcher.matches();


        boolean registerResult = this.userService.registerUser(user, password2);

        if (registerResult) {
            return "redirect:/main";
        } else {
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        this.sessionObject.setUser(null);
        return "redirect:/main";
    }
}
