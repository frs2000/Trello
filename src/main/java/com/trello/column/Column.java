package com.trello.column;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Column {
    private int id;
    @NotNull(message = "Column name can't be missing or empty")
    @Size(min = 3, max = 50, message = "Column name should be between 3 and 50 characters")
    private String name;
    @Min(value = 1, message = "Column serial number can't be less than 1, missing or empty")
    private int serialNumber;

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

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
