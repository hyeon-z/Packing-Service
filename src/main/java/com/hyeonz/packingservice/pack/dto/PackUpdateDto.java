package com.hyeonz.packingservice.pack.dto;

import com.hyeonz.packingservice.pack.domain.Category;

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
