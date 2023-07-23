package com.hyeonz.packingservice.pack.service;

import com.hyeonz.packingservice.pack.domain.Pack;

import java.util.List;

public interface PackService {

    Pack createPack(Pack pack);

    Pack updatePack(Pack pack);

    Pack updateChecked(Long id, boolean checked);

    List<Pack> getPacksByPackingListId(Long packingListId);

    void deletePack(long id);
}
