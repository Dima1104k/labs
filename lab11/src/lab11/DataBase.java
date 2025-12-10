package lab11;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBase {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream in = DataBase.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }


}
