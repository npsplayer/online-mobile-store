package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.UserDto;
import com.email.npsplayer00.store.entity.Role;
import com.email.npsplayer00.store.entity.User;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.exception.RegistrationException;
import com.email.npsplayer00.store.repository.RoleRepository;
import com.email.npsplayer00.store.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.SECRET;
import static com.email.npsplayer00.store.security.SecurityConstants.TOKEN_PREFIX;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public User getUserById(Long id) throws NoSuchEntityException {

        return userRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("User not found"));

    }
    public List<User> getAll() {
        return userRepository.findAllByOrderByIdAsc();
    }

    public void create(UserDto userDto) {
        if (userRepository.findByEmail(userDto.email).isPresent())
            throw new RegistrationException("Email is already.");
        Role role = roleRepository.findRoleById(userDto.role.id);
        User user = new User();
        user.setFirstname(userDto.firstname);
        user.setLastname(userDto.lastname);
        user.setEmail(userDto.email);
        user.setPassword(passwordEncoder.encode(userDto.password));
        user.setBirthday(userDto.birthday);
        user.setPhone(userDto.phone);
        user.setAddress(userDto.address);
        user.setRole(role);
        userRepository.save(user);
    }
    public void edit(UserDto userDto) {
        Role role = roleRepository.findRoleById(userDto.role.id);
        User user = userRepository.findUserById(userDto.Id);
        user.setFirstname(userDto.firstname);
        user.setLastname(userDto.lastname);
        user.setEmail(userDto.email);
        user.setPassword(passwordEncoder.encode(userDto.password));
        user.setBirthday(userDto.birthday);
        user.setPhone(userDto.phone);
        user.setAddress(userDto.address);
        user.setRole(role);
    }

    public void delete(UserDto userDto) {
        User user = userRepository.findUserById(userDto.Id);
        userRepository.delete(user);
    }

    public void register(UserDto userDto) throws NoSuchEntityException, RegistrationException {
        if (userRepository.findByEmail(userDto.email).isPresent())
            throw new RegistrationException("Email is already.");

        User user = new User();
        user.setFirstname(userDto.firstname);
        user.setLastname(userDto.lastname);
        user.setEmail(userDto.email);
        user.setPassword(passwordEncoder.encode(userDto.password));
        if(userRepository.countByRole_Id(1l) == 0) {
            Role role = roleRepository.findById(1l).orElseThrow(() -> new NoSuchEntityException("Role not found"));
            user.setRole(role);
        } else {
            Role role = roleRepository.findById(2l).orElseThrow(() -> new NoSuchEntityException("Role not found"));
            user.setRole(role);
        }
        userRepository.save(user);
    }

    public User getUser(String token) throws NoSuchEntityException {
        String login = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return userRepository.findByEmail(login)
                .orElseThrow(()-> new NoSuchEntityException("no such user."));
    }

    public User editUser(Long userId, User user) {
        User userEdit = userRepository.findUserById(userId);


        if(userEdit.getFirstname() != user.getFirstname()) {
            userEdit.setFirstname(user.getFirstname());
        }
        if(userEdit.getLastname() != user.getLastname()) {
            userEdit.setLastname(user.getLastname());
        }
        if(userEdit.getAddress() != user.getAddress()) {
            userEdit.setAddress(user.getAddress());
        }

            userEdit.setBirthday(user.getBirthday());

        if(userEdit.getPhone() != user.getPhone()) {
            userEdit.setPhone(user.getPhone());
        }

        return userEdit;
    }




}
