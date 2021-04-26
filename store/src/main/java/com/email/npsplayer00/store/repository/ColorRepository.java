package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Color;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
    Color findColorById(Long id);
    List<Color> findAllByOrderByIdAsc();

}
