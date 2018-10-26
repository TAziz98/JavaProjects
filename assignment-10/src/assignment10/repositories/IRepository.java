package assignment10.repositories;

import java.sql.Connection;
import java.util.List;

import assignment10.dtos.DTOBase;

public interface IRepository<TDTO extends DTOBase> {

    Connection getConnection();

    void add(TDTO dto);

    void update(TDTO dto);

    void delete(TDTO dto);

    TDTO findById(int id);
    
    void rollbackTransaction();
    
    List<Integer> getIDs(String sql);
    
    int rowCount();
}
