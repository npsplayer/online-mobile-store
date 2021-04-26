package com.email.npsplayer00.store.repository;

import com.email.npsplayer00.store.entity.Product;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
//    Optional<Product> findProductById(Product product);


    Product findProductById(Long productId);
    List<Product>  findAllByOrderByIdAsc();
    List<Product> findProductsByNameLike(String name);
    List<Product> findProductsByOrderByDatecreateDesc();
    List<Product> findProductsByOrderByPriceAsc();
    List<Product> findProductsByOrderByNameAsc();





















    //cat = 0 color = 0 brand = 0 price = 1
    List<Product> findProductsByPriceBetweenOrderByDatecreateDesc(Float minPrice, Float maxPrice);
    List<Product> findProductsByPriceBetweenOrderByPriceAsc(Float minPrice, Float maxPrice);
    List<Product> findProductsByPriceBetweenOrderByNameAsc(Float minPrice, Float maxPrice);
    //cat = 0 color = 0 brand = 1 price = 0
    List<Product> findProductsByBrand_IdOrderByDatecreateDesc(Long brand_id);
    List<Product> findProductsByBrand_IdOrderByPriceAsc(Long brand_id);
    List<Product> findProductsByBrand_IdOrderByNameAsc(Long brand_id);
    //cat = 0 color = 0 brand = 1 price = 1
    List<Product> findProductsByBrand_IdAndPriceBetweenOrderByDatecreateDesc(Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByBrand_IdAndPriceBetweenOrderByPriceAsc(Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByBrand_IdAndPriceBetweenOrderByNameAsc(Long brand_id, Float minPrice, Float maxPrice);
    //cat = 0 color = 1 brand = 0 price = 0
    List<Product> findProductsByColor_IdOrderByDatecreateDesc(Long color_id);
    List<Product> findProductsByColor_IdOrderByPriceAsc(Long color_id);
    List<Product> findProductsByColor_IdOrderByNameAsc(Long color_id);
    //cat = 0 color = 1 brand = 0 price = 1
    List<Product> findProductsByColor_IdAndPriceBetweenOrderByDatecreateDesc(Long color_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByColor_IdAndPriceBetweenOrderByPriceAsc(Long color_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByColor_IdAndPriceBetweenOrderByNameAsc(Long color_id, Float minPrice, Float maxPrice);
    //cat = 0 color = 1 brand = 1 price = 0
    List<Product> findProductsByColor_IdAndBrand_IdOrderByDatecreateDesc(Long color_id, Long brand_id);
    List<Product> findProductsByColor_IdAndBrand_IdOrderByPriceAsc(Long color_id, Long brand_id);
    List<Product> findProductsByColor_IdAndBrand_IdOrderByNameAsc(Long color_id, Long brand_id);
    //cat = 0 color = 1 brand = 1 price = 1
    List<Product> findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(Long color_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(Long color_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(Long color_id, Long brand_id, Float minPrice, Float maxPrice);
    //cat = 1 color = 0 brand = 0 price = 0
    List<Product> findProductsByCategorie_IdOrderByDatecreateDesc(Long cat_id);
    List<Product> findProductsByCategorie_IdOrderByPriceAsc(Long cat_id);
    List<Product> findProductsByCategorie_IdOrderByNameAsc(Long cat_id);
    //cat = 1 color = 0 brand = 0 price = 1
    List<Product> findProductsByCategorie_IdAndPriceBetweenOrderByDatecreateDesc(Long cat_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndPriceBetweenOrderByPriceAsc(Long cat_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndPriceBetweenOrderByNameAsc(Long cat_id, Float minPrice, Float maxPrice);
    //cat = 1 color = 0 brand = 1 price = 0
    List<Product> findProductsByCategorie_IdAndBrand_IdOrderByDatecreateDesc(Long cat_id, Long brand_id);
    List<Product> findProductsByCategorie_IdAndBrand_IdOrderByPriceAsc(Long cat_id, Long brand_id);
    List<Product> findProductsByCategorie_IdAndBrand_IdOrderByNameAsc(Long cat_id, Long brand_id);
    //cat = 1 color = 0 brand = 1 price = 1
    List<Product> findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(Long cat_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(Long cat_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(Long cat_id, Long brand_id, Float minPrice, Float maxPrice);
    //cat = 1 color = 1 brand = 0 price = 0
    List<Product> findProductsByCategorie_IdAndColor_IdOrderByDatecreateDesc(Long cat_id, Long color_id);
    List<Product> findProductsByCategorie_IdAndColor_IdOrderByPriceAsc(Long cat_id, Long color_id);
    List<Product> findProductsByCategorie_IdAndColor_IdOrderByNameAsc(Long cat_id, Long color_id);
    //cat = 1 color = 1 brand = 0 price = 1
    List<Product> findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByDatecreateDesc(Long cat_id, Long color_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByPriceAsc(Long cat_id, Long color_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByNameAsc(Long cat_id, Long color_id, Float minPrice, Float maxPrice);
    //cat = 1 color = 1 brand = 1 price = 0
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByDatecreateDesc(Long cat_id, Long color_id, Long brand_id);
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByPriceAsc(Long cat_id, Long color_id, Long brand_id);
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByNameAsc(Long cat_id, Long color_id, Long brand_id);
    //cat = 1 color = 1 brand = 1 price = 1
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(Long cat_id, Long color_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(Long cat_id, Long color_id, Long brand_id, Float minPrice, Float maxPrice);
    List<Product> findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(Long cat_id, Long color_id, Long brand_id, Float minPrice, Float maxPrice);



}
