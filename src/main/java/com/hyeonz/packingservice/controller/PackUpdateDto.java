package com.hyeonz.packingservice.controller;

import com.hyeonz.packingservice.model.Category;

public class PackUpdateDto {
    private Long id;
    private String name;
    private Category category;
    private boolean checked;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isChecked() {
        return checked;
    }
}
