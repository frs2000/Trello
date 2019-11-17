package com.trello.board;

import com.trello.column.Column;
import com.trello.column.ColumnService;
import com.trello.column.ColumnValidation;
import com.trello.column.dto.ColumnBoardDTO;
import com.trello.column.dto.ColumnConverterDTO;
import com.trello.task.Task;
import com.trello.task.TaskService;
import com.trello.task.dto.TaskBoardDTO;
import com.trello.task.dto.TaskConverterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private ColumnConverterDTO columnConverterDTO;
    private ColumnService columnService;
    private TaskService taskService;
    private TaskConverterDTO taskConverterDTO;
    private ColumnValidation columnValidation;

    @Autowired
    public BoardServiceImpl(ColumnConverterDTO columnConverterDTO, ColumnService columnService,
                            TaskService taskService, TaskConverterDTO taskConverterDTO, ColumnValidation columnValidation) {
        this.columnConverterDTO = columnConverterDTO;
        this.columnService = columnService;
        this.taskService = taskService;
        this.taskConverterDTO = taskConverterDTO;
        this.columnValidation = columnValidation;
    }

    @Override
    public ColumnBoardDTO getByID(int id){
        columnValidation.IDExist(id);
        Column column = columnService.getByID(id);
        ColumnBoardDTO columnBoardDTO = columnConverterDTO.convertFrom(column);
        List<Task> tasks = taskService.getAllByColumnID(id);
        List<TaskBoardDTO> convertedTasks = taskConverterDTO.convertTaskLict(tasks);
        columnBoardDTO.setTasks(convertedTasks);
        return columnBoardDTO;
    }
}
