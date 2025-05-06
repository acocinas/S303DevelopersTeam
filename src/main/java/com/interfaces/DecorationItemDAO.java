package com.interfaces;
import java.util.List;
import com.dao.exception.DAOException;
import com.enums.Material;
import com.model.DecorationItem;

public interface DecorationItemDAO extends GenericDAO<DecorationItem, Integer> {
    List<DecorationItem> findByMaterial(Material material) throws DAOException;
    List<DecorationItem> findByInteractivity(boolean isInteractive) throws DAOException;
    List<DecorationItem> findDecorationItemsForRoom(Integer roomId) throws DAOException;
    double calculateTotalDecorationValue() throws DAOException;
}
