package board.persistence.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MigrationStrategy {

    private final Connection connection;

    public void executeMigration() {
        var originalOut = System.out;
        var originalErr = System.err;
        
        try (var fos = new FileOutputStream("liquibase.log")) {
            System.setOut(new PrintStream(fos));
            System.setErr(new PrintStream(fos));
            
            try (var jdbcConnection = new JdbcConnection(connection)) {
                System.out.println("Configurando Liquibase...");
                
                var liquibase = new Liquibase(
                    "/db/changelog/db.changelog-master.yml",
                    new ClassLoaderResourceAccessor(),
                    jdbcConnection
                );
                
                System.out.println("Executando migrações...");
                liquibase.update();
                System.out.println("Migração concluída com sucesso!");
            }
            
        } catch (IOException | LiquibaseException ex) {
            ex.printStackTrace();
        } finally {
            System.setErr(originalErr);
            System.setOut(originalOut);
            System.out.println("Verifique o arquivo liquibase.log para detalhes");
        }
    }
}