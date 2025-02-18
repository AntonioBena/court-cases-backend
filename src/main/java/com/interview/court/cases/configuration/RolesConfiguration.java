package com.interview.court.cases.configuration;

import com.interview.court.cases.model.user.Role;
import com.interview.court.cases.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RolesConfiguration {

    private final ApplicationProperties appProperties;
    private final RoleRepository roleRepository;

    @Bean
    public List<String> initializeRoles() {

        List<String> roles = appProperties.getListOfRoles();
        roles.forEach(r -> {
            var role = new Role();
            role.setName(r);
            roleRepository.save(role);
            log.info("Role : {}", r);
        });
        return roles;
    }
}