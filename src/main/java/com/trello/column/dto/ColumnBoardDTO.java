package com.trello.column.dto;

import com.trello.task.dto.TaskBoardDTO;
import java.util.List;

public class ColumnBoardDTO {
    private int id;
    private String name;
    private int serialNumber;
    private List<TaskBoardDTO> tasks;

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

    public List<TaskBoardDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskBoardDTO> tasks) {
        this.tasks = tasks;
    }
}
