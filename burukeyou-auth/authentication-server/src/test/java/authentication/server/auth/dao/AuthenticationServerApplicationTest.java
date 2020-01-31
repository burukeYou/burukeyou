package authentication.server.auth.dao;

import authentication.server.auth.AuthenticationServerApplication;
import authentication.server.auth.entity.vo.UserTokenVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthenticationServerApplication.class)
public class AuthenticationServerApplicationTest {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private PasswordEncoder p;

    @Test
    public void get(){
        //umsAdminMapper.getByUniqueId("burukeyou");
        String encode = p.encode("123456");

        System.out.println(encode);
    }
}
