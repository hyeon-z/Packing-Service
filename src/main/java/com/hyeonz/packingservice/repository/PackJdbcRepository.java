package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.Category;
import com.hyeonz.packingservice.model.Pack;

import java.util.List;

public class PackJdbcRepository implements PackRepository {
    @Override
    public Pack insert(Pack pack) {
        return null;
    }

    @Override
    public Pack update(Pack pack) {
        return null;
    }

    @Override
    public List<Pack> findByCategory(Category category) {
        return null;
    }

    @Override
    public void delete() {

    }
}
