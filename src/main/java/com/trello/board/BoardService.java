package com.trello.board;

import com.trello.column.dto.ColumnBoardDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    ColumnBoardDTO getByID(int id);
}
