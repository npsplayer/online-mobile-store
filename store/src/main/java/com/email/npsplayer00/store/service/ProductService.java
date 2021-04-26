package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.ProductDto;
import com.email.npsplayer00.store.entity.Brand;
import com.email.npsplayer00.store.entity.Categorie;
import com.email.npsplayer00.store.entity.Color;
import com.email.npsplayer00.store.entity.Product;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.repository.BrandRepository;
import com.email.npsplayer00.store.repository.CategorieRepository;
import com.email.npsplayer00.store.repository.ColorRepository;
import com.email.npsplayer00.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final CategorieRepository categorieRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, ColorRepository colorRepository, CategorieRepository categorieRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.categorieRepository = categorieRepository;
    }

    public Product getProductById(Long id) throws NoSuchEntityException {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Product not found"));
    }

    public List<Product> getAllProduct() {
        return productRepository.findAllByOrderByIdAsc();
    }
    public List<Product> searchProduct(String name) {
        String pattern = "%" + name + "%";
        List<Product> search = null;
        List<Product> seachByName = productRepository.findProductsByNameLike(pattern);
        List<Product> seachByNone = productRepository.findProductsByNameLike("searchnone");
        if(name.equals("")) {
            search = seachByNone;
        } else {
            search = seachByName;
        }
        return search;
    }
    public void create(ProductDto productDto) {
        Brand brand = brandRepository.findBrandById(productDto.brand.id);
        Color color = colorRepository.findColorById(productDto.color.id);
        Categorie categorie = categorieRepository.findCategorieById(productDto.categorie.id);
        Product product = new Product();
        product.setName(productDto.name);
        product.setBrand(brand);
        product.setColor(color);
        product.setCategorie(categorie);
        product.setPrice(productDto.price);
        product.setDatecreate(productDto.datecreate);
        product.setImageUrl(productDto.imageUrl);
        product.setDescription(productDto.description);
        productRepository.save(product);
    }
    public void edit(ProductDto productDto) {
        Brand brand = brandRepository.findBrandById(productDto.brand.id);
        Color color = colorRepository.findColorById(productDto.color.id);
        Categorie categorie = categorieRepository.findCategorieById(productDto.categorie.id);
        Product product = productRepository.findProductById(productDto.id);
        product.setName(productDto.name);
        product.setBrand(brand);
        product.setColor(color);
        product.setCategorie(categorie);
        product.setPrice(productDto.price);
        product.setDatecreate(productDto.datecreate);
        product.setImageUrl(productDto.imageUrl);
        product.setDescription(productDto.description);
    }

    public void delete(ProductDto productDto) {
        Product product = productRepository.findProductById(productDto.id);
        productRepository.delete(product);
    }

    public List<Product> getAllSortAndFilter(Long cat_id, Long color_id, Long brand_id, Float minPrice, Float maxPrice, String order) {
        List<Product> productSortAndFilter = null;
        if(cat_id == 0){
            if(color_id == 0) {
                if(brand_id == 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByOrderByDatecreateDesc();
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter =  productRepository.findProductsByOrderByPriceAsc();
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByOrderByNameAsc();
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByPriceBetweenOrderByDatecreateDesc(minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByPriceBetweenOrderByPriceAsc(minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByPriceBetweenOrderByNameAsc(minPrice, maxPrice);
                        }
                    }
                } else if(brand_id > 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdOrderByDatecreateDesc(brand_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdOrderByPriceAsc(brand_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdOrderByNameAsc(brand_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdAndPriceBetweenOrderByDatecreateDesc(brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdAndPriceBetweenOrderByPriceAsc(brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByBrand_IdAndPriceBetweenOrderByNameAsc(brand_id, minPrice, maxPrice);
                        }
                    }
                }
            } else if(color_id > 0) {
                if(brand_id == 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdOrderByDatecreateDesc(color_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdOrderByPriceAsc(color_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdOrderByNameAsc(color_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndPriceBetweenOrderByDatecreateDesc(color_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndPriceBetweenOrderByPriceAsc(color_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndPriceBetweenOrderByNameAsc(color_id, minPrice, maxPrice);
                        }
                    }
                } else if(brand_id > 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdOrderByDatecreateDesc(color_id, brand_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdOrderByPriceAsc(color_id, brand_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdOrderByNameAsc(color_id, brand_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(color_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(color_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByColor_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(color_id, brand_id, minPrice, maxPrice);
                        }
                    }
                }
            }

        } else if(cat_id > 0) {
            System.out.println(1);
            if(color_id == 0) {
                if(brand_id == 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdOrderByDatecreateDesc(cat_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdOrderByPriceAsc(cat_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdOrderByNameAsc(cat_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndPriceBetweenOrderByDatecreateDesc(cat_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndPriceBetweenOrderByPriceAsc(cat_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndPriceBetweenOrderByNameAsc(cat_id, minPrice, maxPrice);
                        }
                    }
                } else if(brand_id > 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdOrderByDatecreateDesc(cat_id, brand_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdOrderByPriceAsc(cat_id, brand_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdOrderByNameAsc(cat_id, brand_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(cat_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(cat_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(cat_id, brand_id, minPrice, maxPrice);
                        }
                    }
                }
            } else if(color_id > 0) {
                System.out.println(2);
                if(brand_id == 0) {
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdOrderByDatecreateDesc(cat_id, color_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdOrderByPriceAsc(cat_id, color_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdOrderByNameAsc(cat_id, color_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByDatecreateDesc(cat_id, color_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByPriceAsc(cat_id, color_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndPriceBetweenOrderByNameAsc(cat_id, color_id, minPrice, maxPrice);
                        }
                    }
                } else if(brand_id > 0) {
                    System.out.println(3);
                    if(minPrice == 0 && maxPrice == 0) {
                        if(order.equalsIgnoreCase("default")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByDatecreateDesc(cat_id, color_id, brand_id);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByPriceAsc(cat_id, color_id, brand_id);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdOrderByNameAsc(cat_id, color_id, brand_id);
                        }
                    } else if(minPrice > 0 && maxPrice > 0 && minPrice < maxPrice) {
                        if(order.equalsIgnoreCase("default")) {

                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByDatecreateDesc(cat_id, color_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("price")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByPriceAsc(cat_id, color_id, brand_id, minPrice, maxPrice);
                        } else if(order.equalsIgnoreCase("name")) {
                            productSortAndFilter = productRepository.findProductsByCategorie_IdAndColor_IdAndBrand_IdAndPriceBetweenOrderByNameAsc(cat_id, color_id, brand_id, minPrice, maxPrice);
                        }
                    }
                }
            }
        }
        return productSortAndFilter;
    }

}
