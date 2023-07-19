package com.hyeonz.packingservice.service;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;

import java.util.List;

public interface PackService {

    Pack createPack(Pack pack);

    Pack updatePack(Pack pack);

    List<Pack> getPacksByCategory(Category category);

    void deletePack(long id);
}
