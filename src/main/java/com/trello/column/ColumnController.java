package com.trello.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody Column column) {
        return new ResponseEntity<>(columnService.create(column), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam int columnID) {
        columnService.delete(columnID);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Column column) {
        columnService.update(column);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getByID")
    public ResponseEntity<Column> getByID(@RequestParam int id) {
        return ResponseEntity.ok(columnService.getByID(id));
    }
}
