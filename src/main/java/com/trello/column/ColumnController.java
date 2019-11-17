package com.trello.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/column")
public class ColumnController {
    private ColumnService columnService;

    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PutMapping("/create")
    public int create(@Valid @RequestBody Column column) {
        return columnService.create(column);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int columnID) {
        columnService.delete(columnID);
    }

    @PostMapping("/update")
    public void update(@Valid @RequestBody Column column) {
        columnService.update(column);
    }

    @GetMapping("/getByID")
    public Column getByID(@RequestParam int id) {
        return columnService.getByID(id);
    }
}
