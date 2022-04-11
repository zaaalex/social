package FiveGroup.social.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Info about user")
@Validated
public class User {
    @NotNull
    @Schema(description = "username in social network", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "date of last authorization user", required = true)
    @JsonProperty("lastAuthorization")
    private String lastAuthorization;
}
