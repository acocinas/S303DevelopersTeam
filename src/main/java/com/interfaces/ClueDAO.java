package com.interfaces;

import java.util.List;
import com.dao.exception.DAOException;
import com.model.Clue;

public interface ClueDAO {
    List<Clue> findByTheme(String theme) throws DAOException;
    List<Clue> findByVisibility(boolean visibility) throws DAOException;
    List<Clue> findCluesForRoom(Integer roomId) throws DAOException;
}
