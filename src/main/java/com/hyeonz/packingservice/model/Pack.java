package com.hyeonz.packingservice.model;

import java.time.LocalDateTime;

public class Pack {
    private final long id;
    private final long packingListId;
    private final String name;
    private final Category category;
    private boolean checked;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Pack(long id, long packingListId, String name, Category category) {
        this.id = id;
        this.packingListId = packingListId;
        this.name = name;
        this.category = category;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
