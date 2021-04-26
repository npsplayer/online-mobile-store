package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAllByOrderByIdAsc();
    Role findRoleById(Long roleId);

}
