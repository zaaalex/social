package FiveGroup.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Gets the user's login on authorization from keycloak")
public class Login {
    public Login(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    private String name;
}
