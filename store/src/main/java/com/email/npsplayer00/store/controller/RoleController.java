package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.RoleDto;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.RoleService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> GetRoleAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(roleService.getAll(), RoleDto.class);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> createRole(@RequestHeader(HEADER_STRING) String token, @RequestBody RoleDto roleDto) throws NoSuchEntityException {
        roleService.create(roleDto);
        return Mapper.mapAll(roleService.getAll(), RoleDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> editRole(@RequestHeader(HEADER_STRING) String token, @RequestBody RoleDto roleDto) throws NoSuchEntityException {
        roleService.edit(roleDto);
        return Mapper.mapAll(roleService.getAll(), RoleDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDto> deleteRole(@RequestHeader(HEADER_STRING) String token, @RequestBody RoleDto roleDto) throws NoSuchEntityException {
        roleService.delete(roleDto);
        return Mapper.mapAll(roleService.getAll(), RoleDto.class);
    }
}
