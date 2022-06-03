package FiveGroup.social.database.subscription;

import FiveGroup.social.database.user.UserRepository;
import FiveGroup.social.exeption.SubscribeNotCreatedException;
import FiveGroup.social.exeption.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository=subscriptionRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void subscribe(String username, String subscribeName) throws UserNotFoundException, SubscribeNotCreatedException {
        if (!userRepository.existsByName(username) || !userRepository.existsByName(subscribeName)
                ||username.equals(subscribeName)) {
            throw new UserNotFoundException("Username or subscribeName isn't found");
        }
        else if (subscriptionRepository.existsByNameAndSubscription(username, subscribeName)) {
                throw new SubscribeNotCreatedException("Already subscribe");
        }
        else{
            SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
            subscriptionEntity.setName(username);
            subscriptionEntity.setSubscription(subscribeName);
            subscriptionRepository.saveAndFlush(subscriptionEntity);
        }
    }

    @Override
    public void unsubscribe(String username, String unsubscribeName) throws UserNotFoundException{
        if (!userRepository.existsByName(username) || !userRepository.existsByName(unsubscribeName)
        ||username.equals(unsubscribeName)) {
            throw new UserNotFoundException("Username or unsubscribeName isn't found");
        }
        else {
            subscriptionRepository.deleteById(
                    subscriptionRepository.
                            findSubscriptionEntityByNameAndSubscription(username, unsubscribeName).getId());
        }
    }

    @Override
    public List<SubscriptionEntity> findSubscribers(String name) {
        return subscriptionRepository.findSubscriptionEntitiesByName(name);
    }
}
