package ramos.jefferson.base.service;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import ramos.jefferson.base.dto.UserDTO;
import ramos.jefferson.base.dto.UserMinimalDTO;
import ramos.jefferson.base.entity.User;

public interface UserService extends UserDetailsService{
    
    public UserDTO save(UserDTO userDTO);
    
    public UserDTO update(UserDTO userDTO, long id);
    
    public UserDTO findOne(long id);
    
    public UserMinimalDTO generateMinimalDTO(User ser);
    
    public User getOne(long id);
    
    public Page<UserDTO> findAll(Map<String, String> parameters);

}
