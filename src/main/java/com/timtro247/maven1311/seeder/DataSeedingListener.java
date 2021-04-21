package com.timtro247.maven1311.seeder;

import com.timtro247.maven1311.model.security.Roles;
import com.timtro247.maven1311.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    @Autowired
    public DataSeedingListener(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("USER") == null) {
            roleRepository.save(new Roles("USER"));
        }
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Roles("ADMIN"));
        }
    }
}
