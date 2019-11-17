package com.trello.task;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TaskService {

    int create (Task task);

    void delete (int taskID);

    Task getByID(int id);

    void update(Task task);

    List<Task> getAllByColumnID(int columnID);
}
