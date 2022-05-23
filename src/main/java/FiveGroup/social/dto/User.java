package FiveGroup.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Data
@Schema(description = "Info about user")
@Validated
public class User {
    @NotNull
    @Schema(description = "username (unique) in social network", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "date of last authorization user", required = true)
    @JsonProperty("lastAuthorization")
    private String lastAuthorization;

    @Null
    @Schema(description = "list posts", required = false)
    @JsonProperty("posts")
    private List<Post> posts;

    @Null
    @Schema(description = "list subscribers", required = false)
    @JsonProperty("subscribers")
    private Set<String> subscribers;

    @Null
    @Schema(description = "list subscriptions", required = false)
    @JsonProperty("subscriptions")
    private Set<String> subscriptions;
}
