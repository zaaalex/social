package FiveGroup.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Validated
@Schema (description = "User post information")
public class Post {
    @Null
    @Schema(description = "Id")
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "Text content in post", required = true)
    @JsonProperty("content")
    private String content;

    @NotNull
    @Schema(description = "Image content in post", required = true)
    @JsonProperty("image")
    private String image;

    @NotNull
    @Schema(description = "Time of publications", required = true)
    @JsonProperty("time")
    private String time;
}
