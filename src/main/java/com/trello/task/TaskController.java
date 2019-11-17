package com.trello.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/create")
    public int create(@Valid @RequestBody Task task) {
        return taskService.create(task);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int taskID) {
        taskService.delete(taskID);
    }

    @GetMapping("/getByID")
    public Task getByID(@RequestParam int id) {
        return taskService.getByID(id);
    }

    @PostMapping("/update")
    public void update(@Valid @RequestBody Task task) {
        taskService.update(task);
    }
}
