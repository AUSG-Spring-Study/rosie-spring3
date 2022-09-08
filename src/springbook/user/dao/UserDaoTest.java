package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new DaoFactory().userDao();

        User user = dao.get("rosie");
        System.out.println(user.getId() + " 조회 성공"); // rosie 조회 성공
    }
}
