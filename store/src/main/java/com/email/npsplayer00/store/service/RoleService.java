package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.RoleDto;
import com.email.npsplayer00.store.entity.Role;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.repository.RoleRepository;
import com.email.npsplayer00.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Role> getAll() {
        return roleRepository.findAllByOrderByIdAsc();
    }
    public void create(RoleDto roleDto) {
        Role role = new Role();
        role.setRole(roleDto.role);
        roleRepository.save(role);
    }
    public void edit(RoleDto roleDto) {
        Role role = roleRepository.findRoleById(roleDto.id);
        role.setRole(roleDto.role);
    }

    public void delete(RoleDto roleDto) {
        Role role = roleRepository.findRoleById(roleDto.id);
        roleRepository.delete(role);
    }
}
