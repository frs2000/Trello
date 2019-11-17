package com.trello.task;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepo {
    void create(Task task);

    int getID(Task task);

    int getMaxSerialNumber(int columnIDForTask);

    void delete(int taskID);

    Task getByID(int id);

    void update(Task task);

    List<Task> getTasksStartingFrom(Task task);

    void updateSerialNumber(Task task);

    List<Task> getAllByColumnID(int columnID);

    int getCountOfIDMatches(int id);

    int getCountOfColumnMatches(int columnID);

    int getSerialNumberMatchesCount(Task task);
}
