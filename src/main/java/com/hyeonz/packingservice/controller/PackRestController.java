package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;
import com.hyeonz.packingservice.service.PackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pack")
public class PackRestController {
    private final PackService packService;

    public PackRestController(PackService packService) {
        this.packService = packService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Pack createPack(Pack pack) {

    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Pack update(Pack pack) {

    }

    @GetMapping({"/category"})
    @ResponseStatus(HttpStatus.OK)
    public List<Pack> findByCategory(Category category) {

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(long id) {

    }
}
