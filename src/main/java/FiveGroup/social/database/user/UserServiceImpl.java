package FiveGroup.social.database.user;

import FiveGroup.social.dto.Post;
import FiveGroup.social.dto.User;
import FiveGroup.social.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public void updateDataAuthorization(String name, Date date) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setLastAuthorization(date.toString());
            userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<User> userList = userEntityList.stream().map(c -> {
            User user=new User();
            user.setName(c.getName());
            user.setLastAuthorization(c.getLastAuthorization());
            user.setSubscribers(c.getSubscribers());
            user.setSubscriptions(c.getSubscriptions());

            List <Post> postList = c.getPosts().stream().map(
                    PostEntity -> {
                        Post post=new Post();
                        post.setId(PostEntity.getId());
                        post.setImage(PostEntity.getImage());
                        post.setContent(PostEntity.getContent());
                        post.setTime(PostEntity.getTime());
                        return post;
                    }).collect(Collectors.toList());

            user.setPosts(postList);
            return user;
        }).collect(Collectors.toList());
        return userList;
    }

    @Override
    public UserEntity seachUser(String name) {
        if (userRepository.existsByName(name)) return userRepository.findUserEntityByName(name);
        else return null;
    }

    @Override
    public void subscribe(String username, String subscribeName) throws UserNotFoundException{
        if (!userRepository.existsByName(username) || !userRepository.existsByName(subscribeName)) {
            throw new UserNotFoundException("Username or subscribeName isn't found");
        }
        else {
            UserEntity userEntity = userRepository.findUserEntityByName(username);
            Set<String> subscribes = userEntity.getSubscribers();
            subscribes.add(subscribeName);
            userEntity.setSubscribers(subscribes);

            userRepository.saveAndFlush(userEntity);
        }
    }

    @Override
    public void unsubscribe(String username, String unsubscribeName) throws UserNotFoundException{
        if (!userRepository.existsByName(username) || !userRepository.existsByName(unsubscribeName)) {
            throw new UserNotFoundException("Username or unsubscribeName isn't found");
        }
        else {
            UserEntity userEntity = userRepository.findUserEntityByName(username);
            Set<String> subscribes = userEntity.getSubscribers();
            subscribes.remove(unsubscribeName);
            userEntity.setSubscribers(subscribes);

            userRepository.saveAndFlush(userEntity);
        }
    }
}
