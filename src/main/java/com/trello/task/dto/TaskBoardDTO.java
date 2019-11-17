package com.trello.task.dto;

import java.time.LocalDate;

public class TaskBoardDTO {
    private int id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private int serialNumber;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }
}
