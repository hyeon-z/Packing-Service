package com.hyeonz.packingservice.model;

import java.time.LocalDateTime;

public class Pack {
    private long id;
    private final long packingListId;
    private final String name;
    private final Category category;
    private boolean checked;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Pack(long packingListId, String name, Category category) {
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Pack(long id, long packingListId, String name, Category category, boolean checked, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
        this.checked = checked;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public long getPackingListId() {
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

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
