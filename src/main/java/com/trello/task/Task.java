package com.trello.task;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Task {
    private int id;
    @NotNull(message = "Task name can't be missing or empty")
    @Size(min = 3, max = 50, message = "Task name should be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Task description can't be missing or empty")
    @Size(min = 3, max = 300, message = "Task description should be between 3 and 300 characters")
    private String description;
    @PastOrPresent(message = "Task creation date can't be in the future")
    private LocalDate creationDate;
    private int serialNumber;
    @Min(value = 1, message = "Column ID can't be less than 1, missing or empty")
    private int columnID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getColumnID() {
        return columnID;
    }

    public void setColumnID(int columnID) {
        this.columnID = columnID;
    }
}
