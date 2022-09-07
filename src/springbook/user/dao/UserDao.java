package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        /*
         * Loading class `com.mysql.jdbc.Driver'.
         * This is deprecated.
         * The new driver class is `com.mysql.cj.jdbc.Driver'.
         * The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
         */
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "root", "rkdudmysql4_");
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "root", "rkdudmysql4_");
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("rosie");
        user.setName("윤로지");
        user.setPassword("hellohello");

        dao.add(user);
        System.out.println(user.getId() +" 등록성공");

        User user2 = dao.get("rosie");
        System.out.println(user2.getId() + " 조회 성공");
        System.out.println("Name:" + user2.getName());
        System.out.println("Password: " + user2.getPassword());
    }
}
