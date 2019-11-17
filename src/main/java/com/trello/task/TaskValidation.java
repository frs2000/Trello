package com.trello.task;

import com.trello.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskValidation {
    private static Logger log = LoggerFactory.getLogger(TaskValidation.class);
    private TaskRepo taskRepo;

    @Autowired
    public TaskValidation(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void IDExist(int id) {
        if (taskRepo.getCountOfIDMatches(id) == 0) {
            log.info(String.format("Task with id = %s, doesn't exist in DB", id));
            throw new NotFoundException("Your task doesn't exist!");
        }
    }

    public boolean containsDate(Task task) {
        if (task.getCreationDate() != null || !task.getCreationDate().toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }

    public void sequenceNumberNotZero(int sequenceNumber) {
        if (sequenceNumber == 0) {
            log.info(String.format("Task sequence number = %s less than 1", sequenceNumber));
            throw new NotFoundException("Task sequence number can't be less than 1, missing or empty");
        }
    }

    public void columnExist(int columnID) {
        if (taskRepo.getCountOfColumnMatches(columnID) == 0) {
            log.info(String.format("Task with column id = %s, doesn't exist in DB", columnID));
            throw new NotFoundException("Column doesn't exist for your task!");
        }
    }

    public boolean containsGapForSerialNumber(Task task) {
        return taskRepo.getSerialNumberMatchesCount(task) == 0 ? true : false;
    }
}

