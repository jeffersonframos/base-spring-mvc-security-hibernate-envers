package ramos.jefferson.base.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ramos.jefferson.base.dto.UserDTO;
import ramos.jefferson.base.dto.UserMinimalDTO;
import ramos.jefferson.base.entity.User;

public interface UserService extends AbstractBaseService<User, UserDTO>, UserDetailsService {
    
    public UserMinimalDTO generateMinimalDTO(User ser);

}
