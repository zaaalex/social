package FiveGroup.social.database.subscription;


import FiveGroup.social.exeption.SubscribeNotCreatedException;
import FiveGroup.social.exeption.UserNotFoundException;

import java.util.List;

public interface SubscriptionService {
    void subscribe(String username, String subscribeName) throws UserNotFoundException, SubscribeNotCreatedException;
    void unsubscribe(String username, String unsubscribeName) throws UserNotFoundException;
    List<SubscriptionEntity> findSubscribers(String name);
}
