package pl.coderslab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {

    private static final String DB_URL = "jdbc:mysql://localhost@3306/szkola_programowania?useSSL=false&characterEncoding=utf&serverTimezone=UTF";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "coderslab";

    private DBUtil() {
    } //tworzymy prywatny konstruktor ktory uniemozliwia utworzenie obiektu tej klasy. Kwestie bezpieczenastwa

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }


}
