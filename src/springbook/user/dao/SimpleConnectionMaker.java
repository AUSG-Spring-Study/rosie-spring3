package springbook.user.dao;

import java.sql.Connection;

public class SimpleConnectionMaker {
    public Connection getConnection() {
        // 문제: D사와 N사에서 확장해서 사용할 수 없게 되었다.
        return null;
    }
}
