package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.Category;

public class PackUpdateDto {
    private Long id;
    private Long packingListId;
    private String name;
    private Category category;


    public Long getId() {
        return id;
    }

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
