package FiveGroup.social.rest.controller;

import FiveGroup.social.database.user.UserService;
import FiveGroup.social.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="home")
    public String home(Model model, Principal principal){
        updateLoginTime(model, principal);
        return "home";
    }

    @PostMapping(value="seach")
    public String seach(Model model, String name){
        if(userService.seachUser(name)!=null) {
            model.addAttribute("user", userService.seachUser(name));
            return "redirect:/user/userseach";
        }
        else return "redirect:/user/home";
        //переделать на страницу *пользователь не найден*
    }

    @GetMapping(value="seachh")
    public String seachh(Model model, Principal principal){
        updateLoginTime(model, principal);
        return "seachh";
    }

    void updateLoginTime(Model model, Principal principal){
        Login login = new Login(principal.getName());
        model.addAttribute("login", login);
        userService.updateDataAuthorization(login.getName(), new Date());
    }
}
