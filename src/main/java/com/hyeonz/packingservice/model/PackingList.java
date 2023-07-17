package com.hyeonz.packingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PackingList {
    private long id;
    private final String title;
    private String description;
    private final LocalDate departureDate;
    private List<Pack> packs;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PackingList(String title, String description, LocalDate departureDate) {
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
