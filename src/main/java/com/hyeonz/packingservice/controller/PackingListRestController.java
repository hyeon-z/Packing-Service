package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.PackingList;
import com.hyeonz.packingservice.service.PackingListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/packingList")
public class PackingListRestController {
    private final PackingListService packingListService;

    public PackingListRestController(PackingListService packingListService) {
        this.packingListService = packingListService;
    }

    @PostMapping
    public PackingList createPackingList(@RequestBody PackingListCreateDto packingListCreateDto) {
        return null;
    }

    @GetMapping
    public List<PackingList> getAllPackingLists() {
        return null;
    }

    @GetMapping("/{id}")
    private Optional<PackingList> getPackingList(@PathVariable long id) {
        return Optional.empty();
    }

    @PatchMapping
    private PackingList updatePackingList(@RequestBody PackingListUpdateDto packingListUpdateDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    private void deletePackingList(@PathVariable Long id) {

    }
}
