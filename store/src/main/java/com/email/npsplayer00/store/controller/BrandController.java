package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.BrandDto;
import com.email.npsplayer00.store.entity.Brand;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.BrandService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("{id}")
    public BrandDto getBrandById(@PathVariable Long id) throws NoSuchEntityException {
        Brand brand = brandService.getBrandById(id);
        return Mapper.map(brand, BrandDto.class);
    }
    @GetMapping("get")
    public List<Brand> getBrandAll() throws NoSuchEntityException {
        return Mapper.mapAll(brandService.getAllBrand(), Brand.class);
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Brand> brandAll(@RequestHeader(HEADER_STRING) String token) throws NoSuchEntityException {
        return Mapper.mapAll(brandService.getAllBrand(), Brand.class);
    }
    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BrandDto> createProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody BrandDto brandDto) throws NoSuchEntityException {
        brandService.create(brandDto);
        return Mapper.mapAll(brandService.getAllBrand(), BrandDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BrandDto> editProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody BrandDto brandDto) throws NoSuchEntityException {
        brandService.edit(brandDto);
        return Mapper.mapAll(brandService.getAllBrand(), BrandDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BrandDto> deleteProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody BrandDto brandDto) throws NoSuchEntityException {
        brandService.delete(brandDto);
        return Mapper.mapAll(brandService.getAllBrand(), BrandDto.class);
    }
}
