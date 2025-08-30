package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import board.dto.BoardDetailDTO;
import board.persistence.dao.BoardColumnDAO;
import board.persistence.dao.BoardDAO;
import board.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardQueryService {
    private final Connection connection;

    public Optional<BoardEntity> findByID(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findByID(id);
        if (optional.isPresent()) {
            var entity = optional.get();
            entity.setBoardColumns(boardColumnDAO.findByBoardID(entity.getId()));
            return Optional.of(entity);
        }
        return Optional.empty();
    }

    public Optional<BoardDetailDTO> showBoardDetails(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findByID(id);
        if (optional.isPresent()) {
            var entity = optional.get();
            entity.setBoardColumns(boardColumnDAO.findByBoardID(id));
            var columns = boardColumnDAO.findByBoardIDWithDetails(entity.getId());
            var dto = new BoardDetailDTO(entity.getId(), entity.getName(), columns);
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
