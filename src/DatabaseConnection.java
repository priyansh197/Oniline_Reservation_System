
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/online_reservation";

    private static final String USER = "root";

    private static final String PASSWORD = "1234";

   public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL JDBC driver is not on the classpath.", e);
    }

    return DriverManager.getConnection(URL, USER, PASSWORD);
}
}
