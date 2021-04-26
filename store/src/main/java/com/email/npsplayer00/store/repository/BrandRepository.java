package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findBrandById(Long id);
    List<Brand> findAllByOrderByIdAsc();

}
