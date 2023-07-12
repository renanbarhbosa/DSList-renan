package com.renan.DSList.controllers;

import com.renan.DSList.dtos.GameDTO;
import com.renan.DSList.dtos.GameMinDTO;
import com.renan.DSList.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameMinDTO>> findAll() {
        List<GameMinDTO> list = gameService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id) {
        GameDTO result = gameService.findById(id);
        return result;
    }

    @PostMapping
    public ResponseEntity<GameMinDTO> insert(@RequestBody GameMinDTO dto) {
        dto = gameService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GameMinDTO> update(@PathVariable Long id, @RequestBody GameMinDTO dto) {
        dto =gameService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
