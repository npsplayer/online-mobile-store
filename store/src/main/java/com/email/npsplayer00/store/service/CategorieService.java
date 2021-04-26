package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.CategorieDto;
import com.email.npsplayer00.store.entity.Categorie;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie getCategorieById(Long id) throws NoSuchEntityException {

        return categorieRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Category not found"));

    }

    public List<Categorie> getAllCategories() {

        return (List<Categorie>) categorieRepository.findAllByOrderByIdAsc();
    }

    public void create(CategorieDto categorieDto) {
        Categorie categorie = new Categorie();
        categorie.setName(categorieDto.name);
        categorieRepository.save(categorie);
    }
    public void edit(CategorieDto categorieDto) {
        Categorie categorie = categorieRepository.findCategorieById(categorieDto.id);
        categorie.setName(categorieDto.name);
    }

    public void delete(CategorieDto categorieDto) {
        Categorie categorie = categorieRepository.findCategorieById(categorieDto.id);
        categorieRepository.delete(categorie);
    }
}
