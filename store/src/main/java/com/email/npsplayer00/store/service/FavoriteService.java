package com.email.npsplayer00.store.service;


import com.email.npsplayer00.store.dto.FavoriteDto;
import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.entity.Favorite;
import com.email.npsplayer00.store.entity.Product;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.repository.FavoriteRepository;
import com.email.npsplayer00.store.repository.ProductRepository;
import com.email.npsplayer00.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Favorite> getAll() {
        return favoriteRepository.findAllByOrderByIdAsc();
    }
    public void create(FavoriteDto favoriteDto) {
        Product product = productRepository.findProductById(favoriteDto.product.id);
        User user = userRepository.findUserById(favoriteDto.user.Id);
        Favorite favorite = new Favorite();
        favorite.setProduct(product);
        favorite.setUser(user);
        favoriteRepository.save(favorite);
    }
    public void edit(FavoriteDto favoriteDto) {
        Product product = productRepository.findProductById(favoriteDto.product.id);
        User user = userRepository.findUserById(favoriteDto.user.Id);
        Favorite favorite = favoriteRepository.findFavoriteById(favoriteDto.id);
        favorite.setProduct(product);
        favorite.setUser(user);
    }

    public void delete(FavoriteDto favoriteDto) {
        Favorite favorite = favoriteRepository.findFavoriteById(favoriteDto.id);
        favoriteRepository.delete(favorite);
    }

    public List<Favorite> getFavorite(User user) {
        return favoriteRepository.findFavoritesByUser_Id(user.getId());
    }

    public void deleteFavorite(Long product_id, User user) {
        Product product = productRepository.findProductById(product_id);
        Favorite favorite = favoriteRepository.findFavoriteByProduct_IdAndUser_Id(product.getId(), user.getId());
        favoriteRepository.delete(favorite);
    }


    public boolean checkFavorite(ProductDto product, User user) {
        boolean check = false;
        if (favoriteRepository.countByProduct_IdAndUser_Id(product.id, user.getId()) > 0) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    public boolean addOrDeleteFavorite(ProductDto productDto, User user) {
        boolean flag = false;
        Product product = productRepository.findProductById(productDto.id);
        if(favoriteRepository.countByProduct_IdAndUser_Id(product.getId(), user.getId()) > 0) {
            Favorite favorite = favoriteRepository.findFavoriteByProduct_IdAndUser_Id(product.getId(), user.getId());
            favoriteRepository.delete(favorite);
            flag = false;
        } else {
            Favorite favorite = new Favorite(product, user);
            favoriteRepository.save(favorite);
            flag = true;
        }
        return flag;

    }
}
