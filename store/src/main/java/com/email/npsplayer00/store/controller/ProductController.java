package com.email.npsplayer00.store.controller;

import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.entity.Product;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.service.ProductService;
import com.email.npsplayer00.store.util.Mapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.email.npsplayer00.store.security.SecurityConstants.HEADER_STRING;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ProductDto GetProductById(@PathVariable Long id) throws NoSuchEntityException {
        Product product = productService.getProductById(id);
        return Mapper.map(product, ProductDto.class);
    }
    @GetMapping("all")
    public List<ProductDto> GetProductAll() throws NoSuchEntityException {
        return Mapper.mapAll(productService.getAllProduct(), ProductDto.class);
    }
    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductDto> createProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) throws NoSuchEntityException {
        productService.create(productDto);
        return Mapper.mapAll(productService.getAllProduct(), ProductDto.class);
    }
    @PostMapping("admin/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductDto> editProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) throws NoSuchEntityException {
        productService.edit(productDto);
        return Mapper.mapAll(productService.getAllProduct(), ProductDto.class);
    }
    @PostMapping("admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductDto> deleteProduct(@RequestHeader(HEADER_STRING) String token, @RequestBody ProductDto productDto) throws NoSuchEntityException {
        productService.delete(productDto);
        return Mapper.mapAll(productService.getAllProduct(), ProductDto.class);
    }

    @GetMapping("get")
    public List<ProductDto> GetProductAllSortAndFilter(@RequestParam("category") Long cat_id, @RequestParam("color") Long color_id, @RequestParam("brand") Long brand_id, @RequestParam("minPrice") Float minPrice, @RequestParam("maxPrice") Float maxPrice, @RequestParam("order") String order) throws NoSuchEntityException {
        return Mapper.mapAll(productService.getAllSortAndFilter(cat_id, color_id, brand_id, minPrice, maxPrice, order), ProductDto.class);
    }

    @GetMapping("search")
    public List<ProductDto> searchProducts(@RequestParam("search") String search) throws NoSuchEntityException {
        return Mapper.mapAll(productService.searchProduct(search), ProductDto.class);
    }
}
