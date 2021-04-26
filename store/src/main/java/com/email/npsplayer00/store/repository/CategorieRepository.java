package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long> {
    Categorie findCategorieById(Long id);
    List<Categorie> findAllByOrderByIdAsc();

}
