package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.ColorDto;
import com.email.npsplayer00.store.entity.Color;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.ColorService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("{id}")
    public ColorDto GetColorById(@PathVariable Long id) throws NoSuchEntityException {
        Color color = colorService.getColorById(id);
        return Mapper.map(color, ColorDto.class);
    }
    @GetMapping("get")
    public List<Color> getColorAll() throws NoSuchEntityException {
        return Mapper.mapAll(colorService.getAllColor(), Color.class);
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Color> ColorAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(colorService.getAllColor(), Color.class);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ColorDto> createColor(@RequestHeader(HEADER_STRING) String token, @RequestBody ColorDto colorDto) throws NoSuchEntityException {
        colorService.create(colorDto);
        return Mapper.mapAll(colorService.getAllColor(), ColorDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ColorDto> editColor(@RequestHeader(HEADER_STRING) String token, @RequestBody ColorDto colorDto) throws NoSuchEntityException {
        colorService.edit(colorDto);
        return Mapper.mapAll(colorService.getAllColor(), ColorDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ColorDto> deleteColor(@RequestHeader(HEADER_STRING) String token, @RequestBody ColorDto colorDto) throws NoSuchEntityException {
        colorService.delete(colorDto);
        return Mapper.mapAll(colorService.getAllColor(), ColorDto.class);
    }
}
