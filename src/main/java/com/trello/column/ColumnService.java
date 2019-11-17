package com.trello.column;

import org.springframework.stereotype.Service;

@Service
public interface ColumnService {

    int create (Column column);

    void delete (int columnID);

    void update(Column column);

    Column getByID(int id);
}
