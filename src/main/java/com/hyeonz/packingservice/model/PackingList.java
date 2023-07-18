package com.hyeonz.packingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PackingList {
    private Long id;
    private String title;
    private String description;
    private final LocalDate departureDate;
    private List<Pack> packs;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

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
    }

    public PackingList(long id, String title, String description, LocalDate departureDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.departureDate = departureDate;
    }

    public Long getId() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addPack(Pack pack) {
        this.packs.add(pack);
    }
}
