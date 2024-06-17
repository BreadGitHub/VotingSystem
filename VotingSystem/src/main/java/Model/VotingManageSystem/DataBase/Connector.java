package Model.VotingManageSystem.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:postgresql://localhost:5432/VotingSystemData";
    private static final String USER = "postgres";
    private static final String PASSWORD = "superuser";
    private final Connection connection;
    public Connector()
    {
        try {
            Class.forName("org.postgresql.Driver"); //Driver
            connection = DriverManager.getConnection(URL,USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {throw new RuntimeException("Error while connection: " + e.getMessage());}
    }
    public Connection getConnection() {return connection;}
}
