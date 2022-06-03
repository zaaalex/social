package FiveGroup.social.database.post;

import FiveGroup.social.dto.Post;
import FiveGroup.social.exeption.PostNotFoundException;
import FiveGroup.social.exeption.UserNotFoundException;

import java.util.List;

public interface PostService {
    void addPost(Post post, String username) throws UserNotFoundException;
    void deletePost(long id, String username) throws PostNotFoundException;
    List<Post> getPosts(String username);
}
