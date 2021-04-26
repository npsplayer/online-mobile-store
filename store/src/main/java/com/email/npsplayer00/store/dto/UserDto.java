package com.email.npsplayer00.store.dto;

import java.util.Date;

public class UserDto {

    public Long Id;
    public String email;
    public String firstname;
    public String lastname;
    public String phone;
    public Date birthday;
    public String address;
    public RoleDto role;
    public String password;

    public UserDto() {
    }


    public UserDto(Long id, String email, String firstname, String lastname, String phone, Date birthday, String address, RoleDto role, String password) {
        Id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.role = role;
        this.password = password;
    }
}
