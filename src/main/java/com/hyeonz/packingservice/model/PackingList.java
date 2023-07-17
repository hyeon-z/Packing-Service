package com.hyeonz.packingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PackingList {
    private final long id;
    private final String title;
    private String description;
    private final LocalDate departureDate;
    private final List<Pack> packs;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PackingList(long id, String title, String description, LocalDate departureDate, List<Pack> packs) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
        this.packs = packs;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public PackingList(long id, String title, LocalDate departureDate, List<Pack> packs) {
        this.id = id;
        this.title = title;
        this.departureDate = departureDate;
        this.packs = packs;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
