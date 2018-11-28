package ramos.jefferson.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageDTO {
    
    @JsonProperty("statusCode")
    private int statusCode;
    
    @JsonProperty("message")
    private String message;

    @Autowired
    public MessageDTO() {
    }

    public MessageDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
