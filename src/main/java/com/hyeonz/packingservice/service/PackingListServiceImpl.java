package com.hyeonz.packingservice.service;

import com.hyeonz.packingservice.model.PackingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackingListServiceImpl implements PackingListService {
    @Override
    public PackingList createPackingList(PackingList packingList) {
        return null;
    }

    @Override
    public List<PackingList> getAllPackingLists() {
        return null;
    }

    @Override
    public Optional<PackingList> getPackingList(long id) {
        return Optional.empty();
    }

    @Override
    public PackingList updatePackingList(PackingList packingList) {
        return null;
    }

    @Override
    public void deletePackingList(Long id) {

    }
}
