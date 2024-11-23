package SYSC4806Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlserver://minishopify.database.windows.net:1433;database=mini_shopify_dev";
    private static final String USER = "admin_minishopify_sysc4806";
    private static final String PASSWORD = "j;*86XgQa^b2+CR5";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
