package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.CategorieDto;
import com.email.npsplayer00.store.entity.Categorie;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.CategorieService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("categorie")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("{id}")
    public CategorieDto GetCategoriesById(@PathVariable Long id) throws NoSuchEntityException {
        Categorie categorie = categorieService.getCategorieById(id);
        return Mapper.map(categorie, CategorieDto.class);
    }
    @GetMapping("get")
    public List<Categorie> getCategoriesAll() throws NoSuchEntityException {
        return Mapper.mapAll(categorieService.getAllCategories(), Categorie.class);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Categorie> CategoriesAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(categorieService.getAllCategories(), Categorie.class);
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategorieDto> createCategorie(@RequestHeader(HEADER_STRING) String token, @RequestBody CategorieDto categorieDto) throws NoSuchEntityException {
        categorieService.create(categorieDto);
        return Mapper.mapAll(categorieService.getAllCategories(), CategorieDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategorieDto> editCategorie(@RequestHeader(HEADER_STRING) String token, @RequestBody CategorieDto categorieDto) throws NoSuchEntityException {
        categorieService.edit(categorieDto);
        return Mapper.mapAll(categorieService.getAllCategories(), CategorieDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategorieDto> deleteCategorie(@RequestHeader(HEADER_STRING) String token, @RequestBody CategorieDto categorieDto) throws NoSuchEntityException {
        categorieService.delete(categorieDto);
        return Mapper.mapAll(categorieService.getAllCategories(), CategorieDto.class);
    }
}
