package org.kortunov.vtbtasks.role.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kortunov.vtbtasks.repository.UserRepository;
import org.kortunov.vtbtasks.role.model.Role;
import org.kortunov.vtbtasks.role.model.User;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.kortunov.vtbtasks.role.model.Privilege.READ;
import static org.kortunov.vtbtasks.role.model.Privilege.WRITE;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHasPrivilege() {
        final var userRole = Role.builder()
                .name("USER")
                .addPrivilege(READ)
                .build();
        final var user = User.builder()
                .addRole(userRole)
                .build();
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        final var authService = new AuthService(userRepository);
        Assertions.assertTrue(authService.hasPrivilege(user.getId(), READ.getId()));
        Assertions.assertFalse(authService.hasPrivilege(user.getId(), WRITE.getId()));
        verify(userRepository, times(2)).findById(user.getId());
    }

    @Test
    public void testGetPrivilegesByUserId() {
        final var userRole = Role.builder()
                .name("USER")
                .addPrivilege(READ)
                .build();
        final var adminRole = Role.builder()
                .name("ADMIN")
                .addPrivilege(READ)
                .addPrivilege(WRITE)
                .build();
        final var user = User.builder()
                .addRole(userRole)
                .build();
        final var admin = User.builder()
                .addRole(userRole)
                .addRole(adminRole)
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.findById(admin.getId())).thenReturn(Optional.of(admin));
        final var authService = new AuthService(userRepository);
        Assertions.assertEquals(Set.of(READ), authService.getPrivilegesByUserId(user.getId()));
        Assertions.assertEquals(Set.of(READ, WRITE), authService.getPrivilegesByUserId(admin.getId()));
        verify(userRepository).findById(user.getId());
        verify(userRepository).findById(admin.getId());
    }
}
