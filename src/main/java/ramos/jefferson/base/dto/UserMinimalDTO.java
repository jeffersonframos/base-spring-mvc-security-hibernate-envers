package ramos.jefferson.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMinimalDTO {
    
    @JsonProperty("id")
    private long id;

    @JsonProperty("username")
    private String username;
    
    @JsonProperty("roles")
    private List<String> roles;
    
    @Autowired
    public UserMinimalDTO() {
        
    }

    public UserMinimalDTO(long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
