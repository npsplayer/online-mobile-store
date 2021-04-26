package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.BrandDto;
import com.email.npsplayer00.store.entity.Brand;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.repository.BrandRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getBrandById(Long id) throws NoSuchEntityException {
        return brandRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Brand not found"));
    }
    public List<Brand> getAllBrand() {
        return brandRepository.findAllByOrderByIdAsc();
    }

    public void create(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setName(brandDto.name);
        brandRepository.save(brand);
    }
    public void edit(BrandDto brandDto) {
        Brand brand = brandRepository.findBrandById(brandDto.id);
        brand.setName(brandDto.name);
    }

    public void delete(BrandDto brandDto) {
        Brand brand = brandRepository.findBrandById(brandDto.id);
        brandRepository.delete(brand);
    }
}
