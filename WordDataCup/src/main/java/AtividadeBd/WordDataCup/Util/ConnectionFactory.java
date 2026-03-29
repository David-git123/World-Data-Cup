package AtividadeBd.WordDataCup.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "MINHA ULR    ";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
