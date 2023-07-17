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
}
