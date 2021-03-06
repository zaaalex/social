package FiveGroup.social.database.user;

import FiveGroup.social.dto.User;
import FiveGroup.social.exeption.UserNotFoundException;

import java.util.Date;
import java.util.List;

public interface UserService {
    void updateDataAuthorization(String username, Date date);
    List<User> getUsers();
    boolean seachUser (String username);
}
