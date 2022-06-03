package FiveGroup.social.rest.controller;

import FiveGroup.social.database.post.PostEntity;
import FiveGroup.social.database.post.PostService;
import FiveGroup.social.database.subscription.SubscriptionService;
import FiveGroup.social.database.user.UserEntity;
import FiveGroup.social.database.user.UserService;
import FiveGroup.social.dto.Login;
import FiveGroup.social.dto.Post;
import FiveGroup.social.exeption.PostNotFoundException;
import FiveGroup.social.exeption.SubscribeNotCreatedException;
import FiveGroup.social.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final PostService postService;
    @Autowired
    public UserController(UserService userService, SubscriptionService subscriptionService, PostService postService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.postService = postService;
    }

    @GetMapping(value="home")
    public String home(Model model, Principal principal){
        updateLoginTime(model, principal);
        model.addAttribute("subscriptions", subscriptionService.findSubscribers(principal.getName()));
        return "home";
    }

    @GetMapping(value="anotherUser/{name}")
    public String anotherUser(Model model, Principal principal, @PathVariable String name){
        updateLoginTime(model, principal);
        model.addAttribute("anotherPosts", postService.getPosts(name));
        if (userService.seachUser(name)) return "anotherUser";
        else return "redirect:/user/notFoundError";
    }

    @GetMapping(value="post")
    public String post(Model model, Principal principal){
        updateLoginTime(model, principal);
        model.addAttribute("posts", postService.getPosts(principal.getName()));
        model.addAttribute("post", new PostEntity());
        return "post";
    }

    @PostMapping(value = "deletePost")
    public String deletePost(Long id, Principal principal) throws PostNotFoundException{
        try {
            userService.updateDataAuthorization(principal.getName(), new Date());
            postService.deletePost(id, principal.getName());
            return "redirect:/user/post";
        }
        catch(PostNotFoundException exception){
            return "redirect:/user/notFoundError";
        }
    }

    @PostMapping(value = "addPost")
    public String addPost(Post post, Principal principal) throws UserNotFoundException{
        try {
            userService.updateDataAuthorization(principal.getName(), new Date());
            postService.addPost(post, principal.getName());
            return "redirect:/user/post";
        }
        catch(UserNotFoundException exception){
            return "redirect:/user/notFoundError";
        }
    }

    @GetMapping(value="notFoundError")
    public String notFoundError(Model model, Principal principal){
        updateLoginTime(model, principal);
        return "notFoundError";
    }

    @GetMapping(value="alreadyExist")
    public String alreadyExist(Model model, Principal principal){
        updateLoginTime(model, principal);
        return "alreadyExist";
    }

    @PostMapping(value = "subscribe")
    public String subscribe(Login login, Principal principal) throws UserNotFoundException, SubscribeNotCreatedException {
        try {
            userService.updateDataAuthorization(principal.getName(), new Date());
            subscriptionService.subscribe(principal.getName(), login.getName());
            return "redirect:/user/home";
        }
        catch(UserNotFoundException exception){
            return "redirect:/user/notFoundError";
        }
        catch (SubscribeNotCreatedException exception){
            return "redirect:/user/alreadyExist";
        }
    }

    @PostMapping(value = "unsubscribe")
    public String unsubscribe(Login login, Principal principal) throws UserNotFoundException {
        try {
            userService.updateDataAuthorization(principal.getName(), new Date());
            subscriptionService.unsubscribe(principal.getName(), login.getName());
            return "redirect:/user/home";
        }
        catch(UserNotFoundException exception){
            return "redirect:/user/notFoundError";
        }
    }

    void updateLoginTime(Model model, Principal principal){
        Login login = new Login(principal.getName());
        model.addAttribute("login", login);
        userService.updateDataAuthorization(login.getName(), new Date());
    }
}
