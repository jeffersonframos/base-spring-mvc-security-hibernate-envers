package ramos.jefferson.base.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ramos.jefferson.base.configuration.security.CustomUserDetails;
import ramos.jefferson.base.dto.LoginDTO;
import ramos.jefferson.base.dto.TokenDTO;
import ramos.jefferson.base.exception.InvalidTokenException;
import ramos.jefferson.base.jwt.JwtTokenUtil;
import ramos.jefferson.base.service.UserService;
import static ramos.jefferson.base.util.Constants.HEADER_STRING;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) userService.loadUserByUsername(loginDTO.getUsername());
        String token = jwtTokenUtil.generateToken(customUserDetails);
        return ResponseEntity.ok(new TokenDTO(token, userService.generateMinimalDTO(customUserDetails.getUser())));
    }
    
    @PutMapping(value = "/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizaToken(@RequestBody TokenDTO tokenDTO) throws InvalidTokenException {
        if(tokenDTO != null && jwtTokenUtil.canTokenBeRefreshed(tokenDTO.getToken())) {
            String refreshedToken = jwtTokenUtil.refreshToken(tokenDTO.getToken());
            return ResponseEntity.ok(new TokenDTO(refreshedToken, tokenDTO.getUserMinimalDTO()));
        }
        throw new InvalidTokenException();
    }
    
    @GetMapping("/verify")
    public ResponseEntity<?> verificaToken(HttpServletRequest request) throws InvalidTokenException {
        String token = request.getHeader(HEADER_STRING);
        if(token != null && jwtTokenUtil.canTokenBeRefreshed(token)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new InvalidTokenException();
    }

}
