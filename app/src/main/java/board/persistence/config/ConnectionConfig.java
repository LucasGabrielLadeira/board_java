package board.persistence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {

    public static Connection getConnection() throws SQLException {
        var url = "jdbc:mysql://localhost/board?useSSL=false&serverTimezone=UTC";
        var user = "root";
        var password = "Luc210701";
        
        System.out.println("Tentando conectar com: " + url);
        var connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        System.out.println("Conexão estabelecida com sucesso!");
        return connection;
    }
}