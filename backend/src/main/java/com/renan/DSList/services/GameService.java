package com.renan.DSList.services;

import com.renan.DSList.dtos.GameMinDTO;
import com.renan.DSList.entities.Game;
import com.renan.DSList.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> page = gameRepository.findAll();
        return page.stream().map(x -> new GameMinDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GameMinDTO findById(Long id) {
        Optional<Game> obj = gameRepository.findById(id);
        Game entity = obj.orElseThrow(() -> new IllegalArgumentException("Not found."));
        return new GameMinDTO(entity);
    }

    @Transactional
    public GameMinDTO insert(GameMinDTO dto) {
            Game entity = new Game();
            entity.setTitle(dto.getTitle());
            entity.setYear(dto.getYear());
            entity.setImgUrl(dto.getImgUrl());
            entity.setShortDescription(dto.getShortDescription());
            entity = gameRepository.save(entity);
            return new GameMinDTO(entity);
    }

    @Transactional
    public GameMinDTO update(Long id, GameMinDTO dto) {
        Game obj = gameRepository.getReferenceById(id);
        obj.setTitle(dto.getTitle());
        obj.setYear(dto.getYear());
        obj.setImgUrl(dto.getImgUrl());
        obj.setShortDescription(dto.getShortDescription());
        return new GameMinDTO(obj);
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
