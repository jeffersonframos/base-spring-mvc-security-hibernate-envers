package ramos.jefferson.base.service.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ramos.jefferson.base.configuration.security.CustomUserDetails;
import ramos.jefferson.base.dto.UserDTO;
import ramos.jefferson.base.dto.UserMinimalDTO;
import ramos.jefferson.base.entity.User;
import ramos.jefferson.base.repository.UserRepository;
import ramos.jefferson.base.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO update(UserDTO userDTO, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO findOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public UserMinimalDTO generateMinimalDTO(User user) {
        return new UserMinimalDTO(user.getId(), user.getUsername(), generateRoles());
    }

    @Override
    public User getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<UserDTO> findAll(Map<String, String> parameters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user with " + username + " found");
        }
        return new CustomUserDetails(user, generateRoles());
    }
    
    private List<String> generateRoles() {
        return Arrays.asList("ADMIN");
    }

}
