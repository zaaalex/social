package FiveGroup.social.rest.controller;

import FiveGroup.social.database.user.UserService;
import FiveGroup.social.dto.Login;
import FiveGroup.social.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="userlist")
    public String home(Model model, Principal principal){
        Login login = new Login(principal.getName());
        model.addAttribute("login", login);
        model.addAttribute("user",  new User());
        model.addAttribute("users",  userService.getUsers());
        return "userlist";
    }
}
