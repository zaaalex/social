package FiveGroup.social.database.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
   boolean existsByNameAndSubscription(String name, String subscription);
   SubscriptionEntity findSubscriptionEntityByNameAndSubscription(String name, String subscription);
   List<SubscriptionEntity> findSubscriptionEntitiesByName(String name);
}
