package com.hyeonz.packingservice.controller;

import java.time.LocalDate;

public class PackingListCreateDto {
    private String title;
    private String description;
    private LocalDate departureDate;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }
}
