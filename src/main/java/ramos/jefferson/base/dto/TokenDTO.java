package ramos.jefferson.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {
    
    @JsonProperty("token")
    private final String token;
    
    @JsonProperty("user")
    private final UserMinimalDTO userMinimalDTO;

    public TokenDTO(String token, UserMinimalDTO userMinimalDTO) {
        this.token = token;
        this.userMinimalDTO = userMinimalDTO;
    }

    public String getToken() {
        return token;
    }

    public UserMinimalDTO getUserMinimalDTO() {
        return userMinimalDTO;
    }

}
