package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.PackingList;
import com.hyeonz.packingservice.service.PackingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public PackingList createPackingList(@RequestBody PackingListCreateDto packingListCreateDto) {
        return packingListService.createPackingList(
                new PackingList(
                        packingListCreateDto.getTitle(),
                        packingListCreateDto.getDescription(),
                        packingListCreateDto.getDepartureDate()
                )
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PackingList> getAllPackingLists() {
        return packingListService.getAllPackingLists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackingList> getPackingList(@PathVariable long id) {
        Optional<PackingList> packingList = packingListService.getPackingList(id);

        if (packingList.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(packingList.get());
    }

    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    public PackingList updatePackingList(@RequestBody PackingListUpdateDto packingListUpdateDto) {
        return packingListService.updatePackingList(new PackingList(
                packingListUpdateDto.getId(),
                packingListUpdateDto.getTitle(),
                packingListUpdateDto.getDescription(),
                packingListUpdateDto.getDepartureDate()
        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePackingList(@PathVariable Long id) {
        packingListService.deletePackingList(id);
    }
}
