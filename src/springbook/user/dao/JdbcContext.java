package springbook.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void executeSQL(String sql) throws SQLException {
        workWithStatementStrategy(new StatementStrategy() {
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement(sql);
                return ps;
            }
        });
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement pStmt = null;

        try {
            c = dataSource.getConnection();
            pStmt = stmt.makePreparedStatement(c);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pStmt != null) {
                try {
                    pStmt.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
