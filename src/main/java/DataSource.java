import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public static Connection getConnection(){

        Connection connection;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qwerty");

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }

        return connection;

    }

}
