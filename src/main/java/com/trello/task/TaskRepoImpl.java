package com.trello.task;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

@Repository
public class TaskRepoImpl implements TaskRepo {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private TaskMapper taskMapper;


    @Autowired
    public TaskRepoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TaskMapper taskMapper, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.taskMapper = taskMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Task task) {
        String sql = "INSERT INTO task (name, description, creation_date, sequence_number, column_id) " +
                "VALUES ( :name , :description, :date, :seqNumber, :columnID)";
        namedParameterJdbcTemplate.update(sql, Map.of("name", task.getName(),
                "description", task.getDescription(), "date", task.getCreationDate(),
                "seqNumber", task.getSerialNumber(), "columnID", task.getColumnID()));
    }

    @Override
    public int getID(Task task) {
        String sql = "SELECT id FROM task WHERE name = :name AND description = :description AND creation_date = :date  " +
                "AND sequence_number = :seqNumber AND column_id = :columnID";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("name", task.getName(),
                "description", task.getDescription(), "date", task.getCreationDate(),
                "seqNumber", task.getSerialNumber(), "columnID", task.getColumnID()), Integer.class);
    }

    @Override
    public int getMaxSerialNumber(int columnIDForTask) {
        String sql = "SELECT MAX(sequence_number) FROM task WHERE column_id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", columnIDForTask), Integer.class);
    }

    @Override
    public void delete(int taskID) {
        String sql = "DELETE FROM task WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, Map.of("id", taskID));
    }

    @Override
    public Task getByID(int id) {
        String sql = "SELECT * FROM task WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), taskMapper);
    }

    @Override
    public void update(Task task) {
        String sql = "UPDATE task SET name = :name , description = :description, creation_date = :date, " +
                "sequence_number = :seqNumber , column_id = :columnID WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, Map.of("name", task.getName(),
                "description", task.getDescription(), "date", task.getCreationDate(),
                "seqNumber", task.getSerialNumber(), "columnID", task.getColumnID(),
                "id", task.getId()));
    }

    @Override
    public List<Task> getTasksStartingFrom(Task task) {
        String sql = "SELECT * FROM task WHERE sequence_number >= :seqNumber AND column_id = :columnID";
        return namedParameterJdbcTemplate.query(sql, Map.of("seqNumber", task.getSerialNumber(),
                "columnID", task.getColumnID()), taskMapper);
    }

    @Override
    public void updateSerialNumber(Task task) {
        String sql = "UPDATE task SET sequence_number = :seqNumber  WHERE id = :id AND name = :name AND description = :description " +
                "AND creation_date = :date AND column_id = :columnID";
        namedParameterJdbcTemplate.update(sql, Map.of("seqNumber", task.getSerialNumber(), "name", task.getName(),
                "description", task.getDescription(), "date", task.getCreationDate(),
                "columnID", task.getColumnID(), "id", task.getId()));
    }

    @Override
    public List<Task> getAllByColumnID(int columnID) {
        String sql = "SELECT * FROM task WHERE column_id = :columnID ORDER BY sequence_number";
        return namedParameterJdbcTemplate.query(sql, Map.of("columnID", columnID), taskMapper);
    }

    @Override
    public int getCountOfIDMatches(int id) {
        String sql = "SELECT COUNT(id) FROM task WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), Integer.class);
    }

    @Override
    public int getCountOfColumnMatches(int columnID) {
        String sql = "SELECT COUNT(column_id) FROM task WHERE column_id = :columnID";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("columnID", columnID), Integer.class);
    }

    @Override
    public int getSerialNumberMatchesCount(Task task) {
        String sql = "SELECT COUNT(sequence_number) FROM task WHERE column_id = :columnID AND sequence_number = :seqNumber";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("columnID", task.getColumnID(),
                "seqNumber", task.getSerialNumber()), Integer.class);
    }
}
