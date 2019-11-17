package com.trello.column;

import com.trello.exception.NotFoundException;
import com.trello.exception.NotUniqueParameterException;
import com.trello.task.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColumnValidation {
    private static Logger log = LoggerFactory.getLogger(ColumnValidation.class);
    private ColumnRepo columnRepo;
    private TaskRepo taskRepo;

    @Autowired
    public ColumnValidation(ColumnRepo columnRepo, TaskRepo taskRepo) {
        this.columnRepo = columnRepo;
        this.taskRepo = taskRepo;
    }

    public void IDExist(int id) {
        if (columnRepo.getCountOfIDMatches(id) == 0) {
            log.info(String.format("Column with id = %s, doesn't exist in DB", id));
            throw new NotFoundException("Your column doesn't exist!");
        }
    }

    public void serialNumberExist(int serialNumber) {
        if (columnRepo.serialNumberValidation(serialNumber) != 0) {
            log.info(String.format("Column with serial number = %s, already exists in DB", serialNumber));
            throw new NotUniqueParameterException("Column with serial number already exists. Enter unique value!");
        }
    }

    public void relatedTasksExist(int columnID){
        if (taskRepo.getCountOfColumnMatches(columnID) != 0) {
            log.info(String.format("Exists related tasks with column id = %s in DB. User can't delete this column", columnID));
            throw new NotFoundException("Exists related tasks. You can't delete chosen column!");
        }
    }

    public boolean containsGapForSerialNumber(int serialNumber) {
        return columnRepo.serialNumberValidation(serialNumber) == 0 ? true : false;
    }
}
