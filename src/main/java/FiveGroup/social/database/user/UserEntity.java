package FiveGroup.social.database.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "userinfo")
public class UserEntity {
    @Setter(AccessLevel.PROTECTED)
    @Schema(description = "username in social network", required = true)
    @Column (name = "name")
    @Id
    private String name;

    @NotNull
    @Setter(AccessLevel.PROTECTED)
    @Schema(description = "date of last authorization user", required = true)
    @JsonProperty("lastAuthorization")
    private String lastAuthorization;
}

