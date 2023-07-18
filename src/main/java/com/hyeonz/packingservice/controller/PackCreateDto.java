package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.Category;

public class PackCreateDto {
    private Long packingListId;
    private String name;
    private Category category;

    public Long getPackingListId() {
        return packingListId;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
