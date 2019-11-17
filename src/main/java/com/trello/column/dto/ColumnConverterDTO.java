package com.trello.column.dto;

import com.trello.column.Column;
import org.springframework.stereotype.Component;

@Component
public class ColumnConverterDTO {

    public ColumnBoardDTO convertFrom(Column column) {
        ColumnBoardDTO columnBoardDTO = new ColumnBoardDTO();
        columnBoardDTO.setId(column.getId());
        columnBoardDTO.setName(column.getName());
        columnBoardDTO.setSerialNumber(column.getSerialNumber());
        return columnBoardDTO;
    }
}
