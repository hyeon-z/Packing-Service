package com.hyeonz.packingservice.controller;

import java.time.LocalDate;

public class PackingListUpdateDto {
    private long id;
    private String title;
    private String description;
    private LocalDate departureDate;


    public long getId() {return id;}

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
