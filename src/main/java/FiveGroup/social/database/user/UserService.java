package FiveGroup.social.database.user;

import FiveGroup.social.dto.user.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void updateUser(String name, Date date);
    List<User> getUsers();
}
