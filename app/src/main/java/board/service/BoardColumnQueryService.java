package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import board.persistence.dao.BoardColumnDAO;
import board.persistence.entity.BoardColumnEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardColumnQueryService {

    private final Connection connection;

    public Optional<BoardColumnEntity> findByID(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(connection);
        return dao.findByID(id);
    }
}
