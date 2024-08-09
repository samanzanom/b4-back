package cl.equifax.apirest.service;

import cl.equifax.apirest.dto.UserDTO;
import cl.equifax.apirest.mapper.UserMapper;
import cl.equifax.apirest.model.User;
import cl.equifax.apirest.repository.UserRepository;
import cl.equifax.apirest.security.JwtTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider tokenProvider;

    private AuthService authService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthService(authenticationManager, userRepository, userMapper, passwordEncoder, tokenProvider);
    }

    @Test
    public void testRegister_SuccessfulRegistration_ReturnsUserDTO() {
        // Arrange
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("newUser");
        String password = "password123";

        User user = new User();
        user.setId(1L);
        user.setUsername("newUser");

        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(1L);
        expectedUserDTO.setUsername("newUser");

        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userMapper.toEntity(inputUserDTO)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(expectedUserDTO);

        // Act
        UserDTO result = authService.register(inputUserDTO, password);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUserDTO.getId(), result.getId());
        assertEquals(expectedUserDTO.getUsername(), result.getUsername());
        verify(userRepository).save(any(User.class));
    }

    @Test(expected = RuntimeException.class)
    public void testRegister_UsernameAlreadyExists_ThrowsException() {
        // Arrange
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("existingUser");
        String password = "password123";

        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));

        // Act & Assert
        authService.register(inputUserDTO, password);
    }

    @Test
    public void testLogin_SuccessfulAuthentication_ReturnsJwtToken() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String expectedToken = "jwtToken123";
        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(username, password);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        when(tokenProvider.generateToken(mockAuthentication)).thenReturn(expectedToken);

        // Act
        String actualToken = authService.login(username, password);

        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test(expected = BadCredentialsException.class)
    public void testLogin_FailedAuthentication_ThrowsBadCredentialsException() {
        // Arrange
        String username = "nonExistentUser";
        String password = "wrongPassword";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // Act & Assert
        authService.login(username, password);
    }
}