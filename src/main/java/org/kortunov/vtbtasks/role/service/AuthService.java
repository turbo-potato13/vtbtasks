package org.kortunov.vtbtasks.role.service;

import lombok.RequiredArgsConstructor;
import org.kortunov.vtbtasks.repository.UserRepository;
import org.kortunov.vtbtasks.role.model.Privilege;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public boolean hasPrivilege(final long userId, final long privilegeId){
        final Privilege privilege = Privilege.fromId(privilegeId);
        return userRepository.findById(userId)
                .stream()
                .flatMap(u -> u.getRoles().stream())
                .flatMap(r -> r.getPrivileges().stream())
                .anyMatch(privilege::equals);
    }

    public Set<Privilege> getPrivilegesByUserId(final long userId){
        return userRepository.findById(userId)
                .stream()
                .flatMap(u -> u.getRoles().stream())
                .flatMap(r -> r.getPrivileges().stream())
                .collect(Collectors.toSet());
    }
}
