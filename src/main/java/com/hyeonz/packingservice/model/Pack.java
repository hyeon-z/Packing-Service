package com.hyeonz.packingservice.model;

import java.time.LocalDateTime;

public class Pack {
    private Long packingListId;
    private Long id;
    private final String name;
    private final Category category;
    private boolean checked;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Pack(Long packingListId, String name, Category category) {
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
    }

    public Pack(Long id, String name, Category category, boolean checked) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.checked = checked;
    }

    public Pack(Long id, Long packingListId, String name, Category category) {
        this.id = id;
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
    }

    public Pack(Long id, Long packingListId, String name, Category category, boolean checked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
        this.checked = checked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public boolean isChecked() {
        return checked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }
}
