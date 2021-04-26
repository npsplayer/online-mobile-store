package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.dto.CartDto;
import com.email.npsplayer00.store.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Long countByProduct_IdAndUser_Id(Long product_id, Long user_id);
    Cart findCartByProduct_IdAndUser_Id(Long product_id, Long user_id);
    List<Cart> findCartsByUser_Id(Long user_id);
    List<Cart>  findAllByOrderByIdAsc();

    Cart findCartById(Long id);
}
