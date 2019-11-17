package com.trello.column;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ColumnRepo {

    void create (Column column);

    int getID (Column column);

    void delete (int columnID);

    void update(Column column);

    Column getByID(int id);

    int getCountOfIDMatches(int id);

    int serialNumberValidation(int serialNumber);

    List<Column> getSerialNumberStartingFrom(int serialNumber);

    void updateSerialNumber(Column column);
}
