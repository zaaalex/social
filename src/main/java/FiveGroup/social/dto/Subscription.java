package FiveGroup.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Info about subscribers and subscriptions")
@Validated
public class Subscription {
    @Null
    @Schema(description = "Id", required = true)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "username", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "he subscription on", required = true)
    @JsonProperty("subscription")
    private String subscription;
}
