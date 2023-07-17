package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;

import java.util.List;
import java.util.Optional;

public class PackingListJdbcRepository implements PackingListRepository {
    @Override
    public List<PackingList> findAll() {
        return null;
    }

    @Override
    public PackingList insert(PackingList packingList) {
        return null;
    }

    @Override
    public PackingList update(PackingList packingList) {
        return null;
    }

    @Override
    public Optional<PackingList> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete() {

    }
}
