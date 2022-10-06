package springbook.user.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;

    // DataSource 오브젝트는 JdbcTemplate을 생성한 후에는 사용하지 않으므로 더이상 멤버변수에 저장해두지 않아도 된다.
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };


    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ? , ?)",
                user.getId(), user.getName(), user.getPassword());
    }


    public User get(String id) {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", userMapper);
    }


    public void deleteAll() {
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("delete from users");
            }
        });

//        미리 준비된 콜백을 만들어서 템플릿을 호출하는 것까지 한번에 해주는 메서드가 존재한다.
//        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        // 원래 getCount() 메소드에 있던 코드 중에서 변하는 부분만 콜백으로 만들어져서 제공된다고 생각하면 이해하기 쉽다.
        return this.jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("select count(*) from users");
            }
        }, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt(1);
            }
        });
//        이렇게도 할 수 있다.
//        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }
}
