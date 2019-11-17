package com.trello.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    private ColumnRepo columnRepo;
    private ColumnValidation columnValidation;

    @Autowired
    public ColumnServiceImpl(ColumnRepo columnRepo, ColumnValidation columnValidation) {
        this.columnRepo = columnRepo;
        this.columnValidation = columnValidation;
    }

    @Override
    public int create(Column column) {
        columnValidation.serialNumberExist(column.getSerialNumber());
        columnRepo.create(column);
        return columnRepo.getID(column);
    }

    @Override
    public void delete(int columnID) {
        columnValidation.relatedTasksExist(columnID);
        columnRepo.delete(columnID);
    }

    @Override
    public void update(Column column) {
        columnValidation.IDExist(column.getId());
        if (!columnValidation.containsGapForSerialNumber(column.getSerialNumber())) {
            makeGapForSerialNumber(column.getSerialNumber());
        }
        columnRepo.update(column);
    }

    private void makeGapForSerialNumber(int serialNumber) {
        List<Column> columns = columnRepo.getSerialNumberStartingFrom(serialNumber);

        for (Column column : columns) {
            int newSerialNumber = column.getSerialNumber() + 1;
            column.setSerialNumber(newSerialNumber);
            columnRepo.updateSerialNumber(column);
        }
    }

    @Override
    public Column getByID(int id) {
        columnValidation.IDExist(id);
        return columnRepo.getByID(id);
    }
}
