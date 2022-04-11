package FiveGroup.social.database.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByName(String name);
    UserEntity findUserEntityByName(String name);
}
