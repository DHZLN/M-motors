package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private final UserService userService = new UserService(userRepository, passwordEncoder);

    @Test
    void inscrire_shouldSaveUser_whenEmailNotExists() {
        User user = new User();
        user.setEmail("test@test.fr");
        user.setPassword("123");

        when(userRepository.findByEmail("test@test.fr")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("123")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.inscrire(user);

        assertNotNull(result);
        assertEquals("CLIENT", result.getRole());
        assertTrue(result.isEnabled());
        verify(userRepository).save(user);
    }

    @Test
    void inscrire_shouldThrowException_whenEmailExists() {
        User user = new User();
        user.setEmail("test@test.fr");

        when(userRepository.findByEmail("test@test.fr"))
                .thenReturn(Optional.of(new User()));

        assertThrows(IllegalArgumentException.class, () -> {
            userService.inscrire(user);
        });
    }

    @Test
    void findByEmail_shouldReturnUser() {
        User user = new User();
        user.setEmail("test@test.fr");

        when(userRepository.findByEmail("test@test.fr"))
                .thenReturn(Optional.of(user));

        User result = userService.findByEmail("test@test.fr");

        assertNotNull(result);
        assertEquals("test@test.fr", result.getEmail());
    }
}