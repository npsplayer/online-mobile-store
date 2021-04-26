package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.CartDto;
import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.entity.Cart;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.CartService;
import com.email.npsplayer00.store.service.UserService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("get")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> getAll(@RequestHeader(HEADER_STRING) String token) {
        User user = userService.getUser(token);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);

    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CartDto> getCartAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(cartService.getAll(), CartDto.class);
    }

    @PostMapping("add")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> addToCart(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.addCart(productDto, user);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);
    }

    @PostMapping("add/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> addToCartAmount(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto, @PathVariable Integer id) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.addCartAmount(productDto, user, id);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);
    }


    @PostMapping("inc/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> incProductCart(@RequestHeader(HEADER_STRING) String token, @PathVariable Long id) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.incProductCart(id, user);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);
    }

    @PostMapping("dec/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> decProductCart(@RequestHeader(HEADER_STRING) String token, @PathVariable Long id) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.decProductCart(id, user);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);
    }

    @PostMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<CartDto> deleteProduct(@RequestHeader(HEADER_STRING) String token, @PathVariable Long id) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.deleteProductCart(id, user);
        return Mapper.mapAll(cartService.getCart(user), CartDto.class);
    }
    @PostMapping("delete")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteProduct(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.deleteAllCart(user);
    }
    @PostMapping("order")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void Order(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        User user = userService.getUser(token);
        cartService.Order(user);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CartDto> createCart(@RequestHeader(HEADER_STRING) String token, @RequestBody CartDto cartDto) throws NoSuchEntityException {
        cartService.create(cartDto);
        return Mapper.mapAll(cartService.getAll(), CartDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CartDto> editCart(@RequestHeader(HEADER_STRING) String token, @RequestBody CartDto cartDto) throws NoSuchEntityException {
        cartService.edit(cartDto);
        return Mapper.mapAll(cartService.getAll(), CartDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CartDto> deleteCart(@RequestHeader(HEADER_STRING) String token, @RequestBody CartDto cartDto) throws NoSuchEntityException {
        cartService.delete(cartDto);
        return Mapper.mapAll(cartService.getAll(), CartDto.class);
    }
}
