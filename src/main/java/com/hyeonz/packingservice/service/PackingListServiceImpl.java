package com.hyeonz.packingservice.service;

import com.hyeonz.packingservice.model.PackingList;
import com.hyeonz.packingservice.repository.PackingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackingListServiceImpl implements PackingListService {
    private final PackingListRepository packingListRepository;

    public PackingListServiceImpl(PackingListRepository packingListRepository) {
        this.packingListRepository = packingListRepository;
    }

    @Override
    public PackingList createPackingList(PackingList packingList) {
        return packingListRepository.insert(packingList);
    }

    @Override
    public List<PackingList> getAllPackingLists() {
        return packingListRepository.findAll();
    }

    @Override
    public Optional<PackingList> getPackingList(long id) {
        return packingListRepository.findById(id);
    }

    @Override
    public PackingList updatePackingList(PackingList packingList) {
        return packingListRepository.update(packingList);
    }

    @Override
    public void deletePackingList(Long id) {
        packingListRepository.deleteById(id);
    }
}
