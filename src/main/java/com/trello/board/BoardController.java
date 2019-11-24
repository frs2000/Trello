package com.trello.board;

import com.trello.column.dto.ColumnBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getByID")
    public ResponseEntity<ColumnBoardDTO> getByID(@RequestParam int id) {
        return new ResponseEntity<>(boardService.getByID(id), HttpStatus.OK);
    }
}
