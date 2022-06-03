package FiveGroup.social.database.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    boolean existsById(Long id);
    List<PostEntity> findPostEntityByUser_Name(String name);
    PostEntity findPostEntityById(Long id);
}
