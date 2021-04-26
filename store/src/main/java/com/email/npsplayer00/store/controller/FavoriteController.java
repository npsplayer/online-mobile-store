package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.CartDto;
import com.email.npsplayer00.store.dto.FavoriteDto;
import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.entity.Favorite;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.FavoriteService;
import com.email.npsplayer00.store.service.UserService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserService userService;


    public FavoriteController(FavoriteService favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping("check")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public boolean checkFavorite(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) {
        User user = userService.getUser(token);
        return favoriteService.checkFavorite(productDto, user);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public boolean addOrDeleteFavorite(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) {
        User user = userService.getUser(token);
        return favoriteService.addOrDeleteFavorite(productDto, user);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FavoriteDto> GetFavoriteAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(favoriteService.getAll(), FavoriteDto.class);
    }

    @GetMapping("get")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<FavoriteDto> getFavorite(@RequestHeader(HEADER_STRING) String token) {
        User user = userService.getUser(token);
        return Mapper.mapAll(favoriteService.getFavorite(user), FavoriteDto.class);
    }

    @PostMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<FavoriteDto> delete(@RequestHeader(HEADER_STRING) String token, @PathVariable Long id) {
        User user = userService.getUser(token);
        favoriteService.deleteFavorite(id, user);
        return Mapper.mapAll(favoriteService.getFavorite(user), FavoriteDto.class);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FavoriteDto> createFavorite(@RequestHeader(HEADER_STRING) String token, @RequestBody FavoriteDto favoriteDto) throws NoSuchEntityException {
        favoriteService.create(favoriteDto);
        return Mapper.mapAll(favoriteService.getAll(), FavoriteDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FavoriteDto> editFavorite(@RequestHeader(HEADER_STRING) String token, @RequestBody FavoriteDto favoriteDto) throws NoSuchEntityException {
        favoriteService.edit(favoriteDto);
        return Mapper.mapAll(favoriteService.getAll(), FavoriteDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FavoriteDto> deleteFavorite(@RequestHeader(HEADER_STRING) String token, @RequestBody FavoriteDto favoriteDto) throws NoSuchEntityException {
        favoriteService.delete(favoriteDto);
        return Mapper.mapAll(favoriteService.getAll(), FavoriteDto.class);
    }
}
