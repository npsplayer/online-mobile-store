package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

    List<History>  findAllByOrderByIdAsc();
    List<History> findHistoriesByUser_Id(Long userId);
    History findHistoryById(Long id);
}
