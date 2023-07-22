package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.Category;

public class PackUpdateDto {
    private String name;
    private Category category;

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
