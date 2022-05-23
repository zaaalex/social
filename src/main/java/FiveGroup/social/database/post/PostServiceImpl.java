package FiveGroup.social.database.post;

import FiveGroup.social.database.user.UserRepository;
import FiveGroup.social.dto.Post;
import FiveGroup.social.exeption.PostNotFoundException;
import FiveGroup.social.exeption.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addPost(Post post, String username) throws UserNotFoundException{
        PostEntity postEntity = new PostEntity();
        if (!userRepository.existsByName(username)) {
            throw new UserNotFoundException("User with name "+username+ " isn't found");
        }
        else {
            postEntity.setUser(userRepository.findUserEntityByName(username));
            postEntity.setImage(post.getImage());
            postEntity.setContent(post.getContent());
            postEntity.setTime(post.getTime());
            postRepository.saveAndFlush(postEntity);
        }
    }

    @Override
    public void deletePost(long id) throws PostNotFoundException {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post with id " + id + " isn't found");
        }
        else{
            postRepository.deleteById(id);
        }
    }

    @Override
    public List<Post> getPosts(String username) throws UserNotFoundException {
        if (!userRepository.existsByName(username)) {
            throw new UserNotFoundException("User with name "+username+ " isn't found");
        }
        else {
           List<PostEntity> postEntityList = postRepository.findPostEntityByUser_Name(username);
           List<Post> postList = postEntityList.stream().map(
                   PostEntity -> {
                       Post post = new Post();
                       post.setId(PostEntity.getId());
                       post.setImage(PostEntity.getImage());
                       post.setContent(PostEntity.getContent());
                       post.setTime(PostEntity.getTime());
                       return post;
                   }).collect(Collectors.toList());

           return postList;
        }
    }
}
