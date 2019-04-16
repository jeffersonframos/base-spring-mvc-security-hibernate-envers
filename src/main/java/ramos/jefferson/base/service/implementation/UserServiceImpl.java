package ramos.jefferson.base.service.implementation;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ramos.jefferson.base.configuration.security.CustomUserDetails;
import ramos.jefferson.base.dto.UserDTO;
import ramos.jefferson.base.dto.UserMinimalDTO;
import ramos.jefferson.base.entity.User;
import ramos.jefferson.base.exception.EmptyParameterException;
import ramos.jefferson.base.exception.ResourceNotFounException;
import ramos.jefferson.base.exception.UserNotFoundException;
import ramos.jefferson.base.repository.UserRepository;
import ramos.jefferson.base.service.UserService;

@Service
public class UserServiceImpl extends AbstractBaseServiceImplementation<User, UserDTO, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
    
    @Override
    public UserMinimalDTO generateMinimalDTO(User user) {
        return new UserMinimalDTO(user.getId(), user.getUsername(), generateRoles());
    }    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = super.repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user with " + username + " found");
        }
        return new CustomUserDetails(user, generateRoles());
    }
    
    @Override
    protected void verify(User t) throws EmptyParameterException {
        if (t == null) {
            throw new EmptyParameterException("User");
        }
        if (StringUtils.isBlank(t.getPassword())) {
            throw new EmptyParameterException("Password");
        }
        if (StringUtils.isBlank(t.getUsername())) {
            throw new EmptyParameterException("Username");
        }
    }

    @Override
    protected void throwResourceNotFoundException() throws ResourceNotFounException {
        throw new UserNotFoundException();
    }
    
    @Override
    protected User convertDtoToType(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        return user;
    }
    
    @Override
    protected UserDTO convertTypeToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        return dto;
    }
    
    private List<String> generateRoles() {
        return Arrays.asList("ADMIN");
    }

}
