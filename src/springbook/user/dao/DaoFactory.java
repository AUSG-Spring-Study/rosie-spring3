package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() { // 메서드의 이름이 빈의 이름이 된다.
        return new UserDao(connectionMaker());
    }

    /*
    *
    * 이런 식으로 다른 Dao에도 ConnectionMaker를 지정해주어야 하는데,
    * Dao의 수가 많아지면 변경해야하는 부분도 많아진다.
    *
    * public AccountDto accountDto() {
    *    return new AccountDao(connectionMaker());
    * }
    *
    * 따라서 ConnectionMaker의 구현클래스를 결정하는 코드를 별도의 메소드로 뽑아낸다.
    * => connectionMaker()
    *
     */

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
