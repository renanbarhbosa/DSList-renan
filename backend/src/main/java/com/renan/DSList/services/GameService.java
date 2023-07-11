package com.renan.DSList.services;

import com.renan.DSList.dtos.GameMinDTO;
import com.renan.DSList.entities.Game;
import com.renan.DSList.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> list = gameRepository.findAll();
        return list.stream().map(x -> new GameMinDTO(x)).collect(Collectors.toList());
    }
}
