package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.HistoryDto;
import com.email.npsplayer00.store.entity.History;
import com.email.npsplayer00.store.entity.Product;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.repository.HistoryRepository;
import com.email.npsplayer00.store.repository.ProductRepository;
import com.email.npsplayer00.store.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public HistoryService(HistoryRepository historyRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.historyRepository = historyRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<History> getHistory(User user) {
        return historyRepository.findHistoriesByUser_Id(user.getId());
    }

    public List<History> getAll() {
        return historyRepository.findAllByOrderByIdAsc();
    }
    public void create(HistoryDto historyDto) {
        Product product = productRepository.findProductById(historyDto.product.id);
        User user = userRepository.findUserById(historyDto.user.Id);
        History history = new History();
        history.setProduct(product);
        history.setUser(user);
        history.setAmount(historyDto.amount);
        history.setPrice(historyDto.price);
        history.setStatus(historyDto.status);
        historyRepository.save(history);
    }
    public void edit(HistoryDto historyDto) {
        Product product = productRepository.findProductById(historyDto.product.id);
        User user = userRepository.findUserById(historyDto.user.Id);
        History history = historyRepository.findHistoryById(historyDto.id);
        history.setProduct(product);
        history.setAmount(historyDto.amount);
        history.setPrice(historyDto.price);
        history.setStatus(historyDto.status);
        history.setUser(user);
    }

    public void delete(HistoryDto historyDto) {
        History history = historyRepository.findHistoryById(historyDto.id);
        historyRepository.delete(history);
    }

}
