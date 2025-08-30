package board.persistence.dao;

import static board.persistence.converter.OffsetDateTimeConverter.toTimestamp;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockDAO {

    private final Connection connection;

    public void block(final Long cardID, final String reason) throws SQLException {
        var sql = "INSERT INTO BLOCKS (blocked_at, block_reason, card_id) VALUES (?, ?, ?);";
        try (var statement = connection.prepareStatement(sql)) {
            var i = 1;
            statement.setTimestamp(i++, toTimestamp(OffsetDateTime.now()));
            statement.setString(i++, reason);
            statement.setLong(i++, cardID);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw (ex);
        }
    }

    public void unblock(final Long cardID, final String reason) throws SQLException {
        var sql = "UPDATE BLOCKS SET unblocked_at = ?, unblock_reason = ? WHERE card_id = ? AND unblock_reason IS NULL;";
        try (var statement = connection.prepareStatement(sql)) {
            var i = 1;
            statement.setTimestamp(i++, toTimestamp(OffsetDateTime.now()));
            statement.setString(i++, reason);
            statement.setLong(i++, cardID);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw (ex);
        }
    }
}
