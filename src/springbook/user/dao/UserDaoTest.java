package springbook.user.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    private UserDao dao;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        dao = new UserDao();
        dao.setDataSource(new SingleConnectionDataSource("jdbc:mysql://localhost/testdb", "root", "rkdudmysql4_", true));
        this.user1 = new User("ididid", "운가용", "sleep");
        this.user2 = new User("IDID99", "로지지징", "gohome");
    }

    @Test // Junit에게 테스트용 메소드임을 알려준다.
    public void addAndGet() throws SQLException, ClassNotFoundException { // JUnit 테스트 메소드는 반드시 public으로 선언돼야한다.
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        dao.add(user1);
        dao.add(user2);

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        User user1 = new User("1111", "홍홍홍", "hongzzi");
        User user2 = new User("2222", "vivian", "vivivivivi");
        User user3 = new User("33333", "matcha", "jmt");

        dao.deleteAll();

        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }
}
