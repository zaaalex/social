package FiveGroup.social.database.post;

import FiveGroup.social.database.subscription.SubscriptionEntity;
import FiveGroup.social.database.subscription.SubscriptionRepository;
import FiveGroup.social.database.subscription.SubscriptionService;
import FiveGroup.social.database.user.UserRepository;
import FiveGroup.social.dto.Post;
import FiveGroup.social.dto.Subscription;
import FiveGroup.social.exeption.PostNotFoundException;
import FiveGroup.social.exeption.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, SubscriptionService subscriptionService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void addPost(Post post, String username) throws UserNotFoundException {
        PostEntity postEntity = new PostEntity();
        if (!userRepository.existsByName(username)) {
            throw new UserNotFoundException("User with name "+username+ " isn't found");
        }
        else {
            postEntity.setUser(userRepository.findUserEntityByName(username));
            postEntity.setContent(post.getContent());
            Date date=new Date();
            date.getTime();
            postEntity.setTime(date.toString());
            postRepository.saveAndFlush(postEntity);
        }
    }

    @Override
    public void deletePost(long id, String username) throws PostNotFoundException {
        if (!postRepository.existsById(id)||!postRepository.findPostEntityById(id).getUser().getName().equals(username)) {
            throw new PostNotFoundException("Post with id " + id + " isn't found");
        }
        else{
            postRepository.deleteById(id);
        }
    }

    @Override
    public List<Post> getPosts(String username){
           List<PostEntity> postEntityList = postRepository.findPostEntityByUser_Name(username);
           List<Post> postList = postEntityList.stream().map(
                   PostEntity -> {
                       Post post = new Post();
                       post.setId(PostEntity.getId());
                       post.setContent(PostEntity.getContent());
                       post.setTime(PostEntity.getTime());
                       return post;
                   }).collect(Collectors.toList());

           return postList;
    }

    @Override
    public List<Post> scroll(String username) {
        List<SubscriptionEntity> subscriptions = subscriptionService.findSubscribers(username);
        List<Post> allPost=subscriptions.stream().map(
                SubscriptionEntity-> getPosts(SubscriptionEntity.getSubscription())
        ).collect(Collectors.toList()).stream().flatMap(Collection::stream).collect(Collectors.toList());
        //из List<List<Post>> приводим к List<Post>

        return allPost;
    }
}
