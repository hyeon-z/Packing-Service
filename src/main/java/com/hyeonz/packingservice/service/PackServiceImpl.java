package com.hyeonz.packingservice.service;

import com.hyeonz.packingservice.model.Pack;
import com.hyeonz.packingservice.repository.PackRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PackServiceImpl implements PackService {
    private final PackRepository packRepository;

    public PackServiceImpl(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    @Override
    public Pack createPack(Pack pack) {
        return packRepository.insert(pack);
    }

    @Override
    public Pack updatePack(Pack pack) {
        return packRepository.update(pack);
    }

    @Override
    public Pack updateChecked(Long id, boolean checked) {
        return packRepository.updateChecked(id, checked);
    }

    @Override
    public List<Pack> getPacksByPackingListId(Long packingListId) {
        List<Pack> packs = packRepository.findByPackingListId(packingListId);

        packs.sort(Comparator.comparing(Pack::getCategory).thenComparing(Pack::getId));

        return packs;
    }

    @Override
    public void deletePack(long id) {
        packRepository.deleteById(id);
    }
}
