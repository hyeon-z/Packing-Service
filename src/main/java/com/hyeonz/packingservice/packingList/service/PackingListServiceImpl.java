package com.hyeonz.packingservice.packingList.service;

import com.hyeonz.packingservice.packingList.domain.PackingList;
import com.hyeonz.packingservice.packingList.repository.PackingListRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        List<PackingList> packingLists = packingListRepository.findAll();

        packingLists.sort(Comparator.comparing(PackingList::getDepartureDate).thenComparing(PackingList::getId));

        return packingLists;
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
