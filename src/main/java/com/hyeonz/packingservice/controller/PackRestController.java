package com.hyeonz.packingservice.controller;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Pack createPack(@RequestBody PackCreateDto packCreateDto) {
        return packService.createPack(new Pack(
                packCreateDto.getPackingListId(),
                packCreateDto.getName(),
                packCreateDto.getCategory()
        ));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public Pack update(@RequestBody PackUpdateDto packUpdateDto) {
        return packService.updatePack(new Pack(
                packUpdateDto.getId(),
                packUpdateDto.getName(),
                packUpdateDto.getCategory(),
                packUpdateDto.isChecked()
        ));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pack> findPackByPackingListId(@RequestParam Long packingListId) {
        return packService.getPacksByPackingListId(packingListId);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id) {
        packService.deletePack(id);
    }
}
