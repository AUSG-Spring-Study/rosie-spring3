package springbook.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class); // 인자: 빈의 이름, 클래스

        /* 만약 클래스를 다르게 생성해서 빈으로 적용하는 메서드가 있다면 이렇게 할 수 있겠지
        @Configuration은 specialUserDao라는 메서드를 갖게 한다
        UserDao dao = context.getBean("specialUserDao", UserDao.class);
        * */

        User user = dao.get("rosie");
        System.out.println(user.getId() + " 조회 성공"); // rosie 조회 성공
    }
}
