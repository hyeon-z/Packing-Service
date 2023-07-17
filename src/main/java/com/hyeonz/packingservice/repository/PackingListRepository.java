package com.hyeonz.packingservice.repository;

import com.hyeonz.packingservice.model.PackingList;

import java.util.List;
import java.util.Optional;

public interface PackingListRepository {
    List<PackingList> findAll();

    PackingList insert(PackingList packingList);

    PackingList update(PackingList packingList);

    Optional<PackingList> findById(long id);

    void deleteById(Long id);

    void deleteAll();
}
