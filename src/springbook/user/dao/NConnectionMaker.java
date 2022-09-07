package springbook.user.dao;

import java.sql.Connection;

public class NConnectionMaker implements ConnectionMaker {
    @Override
    public Connection makeConnection() {
        return null;
    }
}
