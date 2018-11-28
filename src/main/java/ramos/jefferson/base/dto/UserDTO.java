package ramos.jefferson.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDTO extends UserMinimalDTO {

    @JsonProperty("password")
    private String password;

    @Autowired
    public UserDTO() {
        super();
    }

    public UserDTO(long id, String username, String password, List<String> roles) {
        super(id, username, roles);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
