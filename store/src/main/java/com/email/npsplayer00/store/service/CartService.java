package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.CartDto;
import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.dto.UserDto;
import com.email.npsplayer00.store.entity.Cart;
import com.email.npsplayer00.store.entity.History;
import com.email.npsplayer00.store.entity.Product;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.repository.CartRepository;
import com.email.npsplayer00.store.repository.HistoryRepository;
import com.email.npsplayer00.store.repository.ProductRepository;
import com.email.npsplayer00.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository, HistoryRepository historyRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }
    public List<Cart> getAll() {
        return cartRepository.findAllByOrderByIdAsc();
    }
    public void create(CartDto cartDto) {
        Product product = productRepository.findProductById(cartDto.product.id);
        User user = userRepository.findUserById(cartDto.user.Id);
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setAmount(cartDto.amount);
        cart.setPrice(cartDto.price);
        cartRepository.save(cart);
    }
    public void edit(CartDto cartDto) {
        Product product = productRepository.findProductById(cartDto.product.id);
        User user = userRepository.findUserById(cartDto.user.Id);
        Cart cart = cartRepository.findCartById(cartDto.id);
        cart.setProduct(product);
        cart.setUser(user);
        cart.setAmount(cartDto.amount);
        cart.setPrice(cartDto.price);
    }

    public void delete(CartDto cartDto) {
        Cart cart = cartRepository.findCartById(cartDto.id);
        cartRepository.delete(cart);
    }

    public void addCart(ProductDto productDto, User user) {
        Product product = productRepository.findProductById(productDto.id);
        if(cartRepository.countByProduct_IdAndUser_Id(product.getId(), user.getId()) > 0) {
            Cart cartUpdate = cartRepository.findCartByProduct_IdAndUser_Id(product.getId(), user.getId());
            Integer inc = cartUpdate.getAmount() + 1;
            cartUpdate.setAmount(inc);
            cartUpdate.setPrice(product.getPrice() * inc);
        } else {
            Float price = product.getPrice();
            Cart cart = new Cart(product, user, 1, price);
            cartRepository.save(cart);
        }
    }

    public void addCartAmount(ProductDto productDto, User user, Integer amount) {
        if(amount > 0) {
            Product product = productRepository.findProductById(productDto.id);
            if(cartRepository.countByProduct_IdAndUser_Id(product.getId(), user.getId()) > 0) {
                Cart cartUpdate = cartRepository.findCartByProduct_IdAndUser_Id(product.getId(), user.getId());
                Integer inc = cartUpdate.getAmount() + amount;
                cartUpdate.setAmount(inc);
                cartUpdate.setPrice(product.getPrice() * inc);
            } else {
                Float price = product.getPrice();
                Cart cart = new Cart(product, user, amount, price);
                cartRepository.save(cart);
            }
        }

    }
    public List<Cart> getCart(User user) {
        return cartRepository.findCartsByUser_Id(user.getId());
    }

    public void incProductCart(Long productId, User user) {
        Product product = productRepository.findProductById(productId);
        Cart cartUpdate = cartRepository.findCartByProduct_IdAndUser_Id(product.getId(), user.getId());
        Integer inc = cartUpdate.getAmount() + 1;
        cartUpdate.setAmount(inc);
        cartUpdate.setPrice(product.getPrice() * inc);
    }
    public void decProductCart(Long productId, User user) {
        Product product = productRepository.findProductById(productId);
        Cart cartUpdate = cartRepository.findCartByProduct_IdAndUser_Id(product.getId(), user.getId());
        Integer dec = cartUpdate.getAmount() - 1;
        if(dec != 0) {
            cartUpdate.setAmount(dec);
            cartUpdate.setPrice(product.getPrice() * dec);
        } else if(dec == 0) {
            cartRepository.delete(cartUpdate);
        }
    }
    public void deleteProductCart(Long productId, User user) {
        Cart cartDelete = cartRepository.findCartByProduct_IdAndUser_Id(productId, user.getId());
        cartRepository.delete(cartDelete);
    }
    public void deleteAllCart(User user) {
        List<Cart> cartDelete = cartRepository.findCartsByUser_Id(user.getId());
        cartRepository.deleteAll(cartDelete);
    }

    public void Order(User user) {
        List<Cart> allCart = cartRepository.findCartsByUser_Id(user.getId());
        for(Cart cart : allCart) {
            History history =  new History();
            history.setProduct(cart.getProduct());
            history.setUser(cart.getUser());
            history.setStatus("Delivery");
            history.setAmount(cart.getAmount());
            history.setPrice(cart.getPrice());
            historyRepository.save(history);
        }
        cartRepository.deleteAll(allCart);
    }
}
