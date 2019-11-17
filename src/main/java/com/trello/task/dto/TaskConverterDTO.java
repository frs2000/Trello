package com.trello.task.dto;

import com.trello.task.Task;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TaskConverterDTO {

    public TaskBoardDTO convertFrom(Task task) {
        TaskBoardDTO taskBoardDTO = new TaskBoardDTO();
        taskBoardDTO.setId(task.getId());
        taskBoardDTO.setName(task.getName());
        taskBoardDTO.setDescription(task.getDescription());
        taskBoardDTO.setCreationDate(task.getCreationDate());
        taskBoardDTO.setSerialNumber(task.getSerialNumber());
        return taskBoardDTO;
    }

    public List<TaskBoardDTO> convertTaskLict(List<Task> tasks) {
        List<TaskBoardDTO> DTOtasks = new LinkedList<>();
        for (Task temp : tasks) {
            DTOtasks.add(convertFrom(temp));
        }
        return DTOtasks;
    }
}
