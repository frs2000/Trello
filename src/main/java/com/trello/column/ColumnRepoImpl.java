package com.trello.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ColumnRepoImpl implements ColumnRepo {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private ColumnMapper columnMapper;

    @Autowired
    public ColumnRepoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ColumnMapper columnMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.columnMapper = columnMapper;
    }

    @Override
    public void create(Column column) {
        String sql = "INSERT INTO columns (name, serial_number) VALUES ( :name , :number)";
        namedParameterJdbcTemplate.update(sql, Map.of("name", column.getName(),
                "number", column.getSerialNumber()));
    }

    @Override
    public void delete(int columnID) {
        String sql = "DELETE FROM columns WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, Map.of("id", columnID));
    }

    @Override
    public void update(Column column) {
        String sql = "UPDATE columns SET name = :name , serial_number = :number WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, Map.of("name", column.getName(),
                "number", column.getSerialNumber(), "id", column.getId()));
    }

    @Override
    public int getID(Column column) {
        String sql = "SELECT id FROM columns WHERE name = :name AND serial_number = :number";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("name", column.getName(),
                "number", column.getSerialNumber()), Integer.class);
    }

    @Override
    public Column getByID(int id) {
        String sql = "SELECT * FROM columns WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), columnMapper);
    }

    @Override
    public int getCountOfIDMatches(int id) {
        String sql = "SELECT COUNT(id) FROM columns WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), Integer.class);
    }

    @Override
    public int serialNumberValidation(int serialNumber) {
        String sql = "SELECT COUNT(serial_number) FROM columns WHERE serial_number = :serialNumber";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("serialNumber", serialNumber), Integer.class);
    }

    @Override
    public List<Column> getSerialNumberStartingFrom(int serialNumber) {
        String sql = "SELECT * FROM columns WHERE serial_number >= :serialNumber";
        return namedParameterJdbcTemplate.query(sql, Map.of("serialNumber", serialNumber), columnMapper);
    }

    @Override
    public void updateSerialNumber(Column column) {
        String sql = "UPDATE columns SET serial_number = :serialNumber WHERE id = :id AND name = :name ";
        namedParameterJdbcTemplate.update(sql, Map.of("serialNumber", column.getSerialNumber(),
                "id", column.getId(), "name", column.getName()));
    }
}

