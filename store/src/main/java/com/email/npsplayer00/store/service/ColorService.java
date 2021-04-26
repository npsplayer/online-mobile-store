package com.email.npsplayer00.store.service;

import com.email.npsplayer00.store.dto.ColorDto;
import com.email.npsplayer00.store.entity.Color;
import com.email.npsplayer00.store.exception.NoSuchEntityException;
import com.email.npsplayer00.store.repository.ColorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Color getColorById(Long id) throws NoSuchEntityException {
        return colorRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Color not found"));
    }
    public List<Color> getAllColor() {
        return (List<Color>) colorRepository.findAllByOrderByIdAsc();
    }

    public void create(ColorDto colorDto) {
        Color color = new  Color();
        color.setName(colorDto.name);
        color.setColorHex(colorDto.colorHex);
        colorRepository.save(color);

    }
    public void edit(ColorDto colorDto) {
        Color color = colorRepository.findColorById(colorDto.id);
        color.setName(colorDto.name);
        color.setColorHex(colorDto.colorHex);
    }

    public void delete(ColorDto colorDto) {
        Color color = colorRepository.findColorById(colorDto.id);
        colorRepository.delete(color);
    }
}
