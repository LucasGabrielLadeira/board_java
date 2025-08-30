package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import board.dto.CardDetails;
import board.persistence.dao.CardDAO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardQueryService {

    private final Connection connection;

    public Optional<CardDetails> findByID(final Long id) throws SQLException {
        var dao = new CardDAO(connection);
        return dao.findByID(id);
    }

}
