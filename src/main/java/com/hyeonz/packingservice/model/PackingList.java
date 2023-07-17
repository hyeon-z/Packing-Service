package com.hyeonz.packingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PackingList {
    private long id;
    private String title;
    private String description;
    private final LocalDate departureDate;
    private List<Pack> packs;

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PackingList(long id, String title, String description, LocalDate departureDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PackingList(String title, String description, LocalDate departureDate) {
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
