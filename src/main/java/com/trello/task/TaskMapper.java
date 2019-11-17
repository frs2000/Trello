package com.trello.task;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        task.setCreationDate(rs.getDate("creation_date").toLocalDate());
        task.setSerialNumber(rs.getInt("sequence_number"));
        task.setColumnID(rs.getInt("column_id"));
        return task;
    }
}

