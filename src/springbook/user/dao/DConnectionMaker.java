package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/springbook", "root", "rkdudmysql4_");
        } catch (SQLException e) {
            System.out.println("SQLException이 났으요");
        }
        return null;
    }
}
