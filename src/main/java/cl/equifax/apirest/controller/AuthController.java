package cl.equifax.apirest.controller;

import cl.equifax.apirest.dto.JwtAuthenticationResponse;
import cl.equifax.apirest.dto.LoginRequest;
import cl.equifax.apirest.dto.RegisterRequest;
import cl.equifax.apirest.dto.UserDTO;
import cl.equifax.apirest.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  RegisterRequest registerRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(registerRequest.getUsername());
        UserDTO registeredUser = authService.register(userDTO, registerRequest.getPassword());
        return ResponseEntity.ok(registeredUser);
    }
}