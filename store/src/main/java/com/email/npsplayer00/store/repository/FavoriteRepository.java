package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    Long countByProduct_IdAndUser_Id(Long product_id, Long user_id);
    Favorite findFavoriteByProduct_IdAndUser_Id(Long product_id, Long user_id);
    List<Favorite> findFavoritesByUser_Id(Long user_id);
    List<Favorite>  findAllByOrderByIdAsc();
    Favorite findFavoriteById(Long id);
}
