package com.m_motors.mmotors.security;

import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword123");
        testUser.setRole("CLIENT");
        testUser.setEnabled(true);
    }

    @Test
    void loadUserByUsername_shouldReturnUserDetailsWhenUserExists() {
        // GIVEN
        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(testUser));

        // WHEN
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");

        // THEN
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("encodedPassword123", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_CLIENT")));

        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void loadUserByUsername_shouldThrowExceptionWhenUserNotFound() {
        // GIVEN
        when(userRepository.findByEmail("unknown@example.com"))
                .thenReturn(Optional.empty());

        // WHEN & THEN
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername("unknown@example.com")
        );

        assertTrue(exception.getMessage().contains("Utilisateur non trouvé"));
        assertTrue(exception.getMessage().contains("unknown@example.com"));
        verify(userRepository, times(1)).findByEmail("unknown@example.com");
    }

    @Test
    void loadUserByUsername_shouldReturnDisabledUserWhenEnabledIsFalse() {
        // GIVEN
        testUser.setEnabled(false);
        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(testUser));

        // WHEN
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");

        // THEN
        assertNotNull(userDetails);
        assertFalse(userDetails.isEnabled());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void loadUserByUsername_shouldAddRolePrefixCorrectly() {
        // GIVEN
        testUser.setRole("ADMIN");
        when(userRepository.findByEmail("admin@example.com"))
                .thenReturn(Optional.of(testUser));

        // WHEN
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin@example.com");

        // THEN
        assertTrue(userDetails.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        verify(userRepository, times(1)).findByEmail("admin@example.com");
    }
}