package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findUserById(Long userId);
    Long countByRole_Id(Long roleId);
    List<User> findAllByOrderByIdAsc();

}
