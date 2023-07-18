package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;

import java.util.List;

public interface PackRepository {
    List<Pack> findAll();

    Pack insert(Pack pack);

    Pack update(Pack pack);

    List<Pack> findByCategory(Category category);

    void deleteById(long id);

    void deleteAll();
}
