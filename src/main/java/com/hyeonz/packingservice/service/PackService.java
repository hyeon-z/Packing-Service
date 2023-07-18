package com.hyeonz.packingservice.service;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;

import java.util.List;

public interface PackService {

    Pack insert(Pack pack);

    Pack update(Pack pack);

    List<Pack> findByCategory(Category category);

    void deleteById(long id);
}
