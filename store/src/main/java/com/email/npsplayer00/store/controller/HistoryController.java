package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.FavoriteDto;
import com.email.npsplayer00.store.dto.HistoryDto;
import com.email.npsplayer00.store.entity.History;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.HistoryService;
import com.email.npsplayer00.store.service.UserService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("history")
public class HistoryController {

    private final HistoryService historyService;
    private final UserService userService;

    public HistoryController(HistoryService historyService, UserService userService) {
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping("get")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<HistoryDto> getFavorite(@RequestHeader(HEADER_STRING) String token) {
        User user = userService.getUser(token);
        return Mapper.mapAll(historyService.getHistory(user), HistoryDto.class);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryDto> GetHistoryAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(historyService.getAll(), HistoryDto.class);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryDto> createHistory(@RequestHeader(HEADER_STRING) String token, @RequestBody HistoryDto historyDto) throws NoSuchEntityException {
        historyService.create(historyDto);
        return Mapper.mapAll(historyService.getAll(), HistoryDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryDto> editHistory(@RequestHeader(HEADER_STRING) String token, @RequestBody HistoryDto historyDto) throws NoSuchEntityException {
        historyService.edit(historyDto);
        return Mapper.mapAll(historyService.getAll(), HistoryDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryDto> deleteHistory(@RequestHeader(HEADER_STRING) String token, @RequestBody HistoryDto historyDto) throws NoSuchEntityException {
        historyService.delete(historyDto);
        return Mapper.mapAll(historyService.getAll(), HistoryDto.class);
    }
}
