package com.email.npsplayer00.store.controller;


import com.email.npsplayer00.store.dto.UserDto;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.exception.RegistrationException;
import com.email.npsplayer00.store.service.UserService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("{id}")
    public UserDto GetUserById(@PathVariable Long id) throws NoSuchEntityException {
        User user = userService.getUserById(id);
        return Mapper.map(user, UserDto.class);
    }

    @GetMapping("get")
    public UserDto GetUserById(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.map(userService.getUser(token), UserDto.class);
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> GetUserAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(userService.getAll(), UserDto.class);
    }

    @PostMapping("my/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserDto editUser(@RequestHeader(HEADER_STRING) String token, @RequestBody User userDto) throws NoSuchEntityException {
        User user  = userService.getUser(token);
        return Mapper.map(userService.editUser(user.getId(), userDto), UserDto.class);
    }

    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) throws NoSuchEntityException, RegistrationException {
        userService.register(userDto);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> createUser(@RequestHeader(HEADER_STRING) String token, @RequestBody UserDto historyDto) throws NoSuchEntityException {
        userService.create(historyDto);
        return Mapper.mapAll(userService.getAll(), UserDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> editUser(@RequestHeader(HEADER_STRING) String token, @RequestBody UserDto historyDto) throws NoSuchEntityException {
        userService.edit(historyDto);
        return Mapper.mapAll(userService.getAll(), UserDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> deleteUser(@RequestHeader(HEADER_STRING) String token, @RequestBody UserDto historyDto) throws NoSuchEntityException {
        userService.delete(historyDto);
        return Mapper.mapAll(userService.getAll(), UserDto.class);
    }




}
