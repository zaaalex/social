package FiveGroup.social.rest.controller;

import FiveGroup.social.database.user.UserService;
import FiveGroup.social.dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    void updateLoginTime(Model model, Principal principal){
        Login login = new Login(principal.getName());
        model.addAttribute("login", login);
        userService.updateUser(login.getName(), new Date());
    }
}
