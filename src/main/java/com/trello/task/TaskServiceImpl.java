package com.trello.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;
    private TaskValidation taskValidation;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, TaskValidation taskValidation) {
        this.taskRepo = taskRepo;
        this.taskValidation = taskValidation;
    }

    @Override
    public int create(Task task) {
        setDefaultParams(task);
        taskRepo.create(task);
        return taskRepo.getID(task);
    }

    @Override
    public void delete(int taskID) {
        taskRepo.delete(taskID);
    }

    @Override
    public Task getByID(int id) {
        taskValidation.IDExist(id);
        return taskRepo.getByID(id);
    }

    @Override
    public void update(Task task) {
        taskValidation.IDExist(task.getId());
        taskValidation.sequenceNumberNotZero(task.getSerialNumber());
        taskValidation.columnExist(task.getColumnID());
        if (!taskValidation.containsGapForSerialNumber(task)){
            makeGapForID(task);
        }
        taskRepo.update(task);
    }

    @Override
    public List<Task> getAllByColumnID(int columnID) {
        return taskRepo.getAllByColumnID(columnID);
    }

    private void makeGapForID(Task task) {
        List<Task> tasks = taskRepo.getTasksStartingFrom(task);

        for (Task temp : tasks) {
            int newSeqNumber = temp.getSerialNumber() + 1;
            temp.setSerialNumber(newSeqNumber);
            taskRepo.updateSerialNumber(temp);
        }
    }

    private void setDefaultParams(Task task) {
        if (!taskValidation.containsDate(task)) {
            addDate(task);
        }
        addSequenceNumber(task);
    }

    private void addDate(Task task) {
        task.setCreationDate(LocalDate.now());
    }

    private void addSequenceNumber(Task task) {
        int columnID = task.getColumnID();
        int serialNumber = taskRepo.getMaxSerialNumber(columnID) + 1;
        task.setSerialNumber(serialNumber);
    }
}
