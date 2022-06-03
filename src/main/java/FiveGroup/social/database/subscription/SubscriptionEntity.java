package FiveGroup.social.database.subscription;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "subscriptioninfo")
public class SubscriptionEntity {
    @Setter(AccessLevel.NONE)
    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY) Long id;

    @Setter(AccessLevel.PROTECTED)
    @Schema(description = "username", required = true)
    @Column (name = "username")
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @Schema(description = "he subscription on", required = true)
    @Column (name = "subscription")
    private String subscription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
