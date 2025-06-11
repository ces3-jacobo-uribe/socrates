package co.edu.poli.ces3.socrates.socrates.interfaces;

import co.edu.poli.ces3.socrates.socrates.dao.User;

import java.sql.SQLException;
import java.util.List;

public interface ICrud {
    int insert(User user);
    Object update(User user) throws SQLException;
    void delete(User user);
    Object findById(int id);
    List<?> findAll() throws SQLException;
}
