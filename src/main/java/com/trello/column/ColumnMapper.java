package com.trello.column;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ColumnMapper implements RowMapper<Column> {

    @Override
    public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
        Column column = new Column();
        column.setId(rs.getInt("id"));
        column.setName(rs.getString("name"));
        column.setSerialNumber(rs.getInt("serial_number"));
        return column;
    }
}

