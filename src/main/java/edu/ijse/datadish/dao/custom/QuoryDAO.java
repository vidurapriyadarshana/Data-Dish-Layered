package edu.ijse.datadish.dao.custom;

import edu.ijse.datadish.dao.SuperDAO;
import edu.ijse.datadish.dto.OrderTableDto;

import java.util.List;

public interface QuoryDAO extends SuperDAO {
    List<OrderTableDto> loadIncompleteOrders() throws Exception;
}