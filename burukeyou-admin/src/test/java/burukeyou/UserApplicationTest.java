package burukeyou;

import burukeyou.admin.UmsAdminApplication;
import burukeyou.admin.mapper.UmsAdminRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UmsAdminApplication.class)
public class UserApplicationTest {

    @Autowired
    private UmsAdminRoleMapper umsAdminRoleMapper;

    @Test
    public void contextLoads() {
        int i = umsAdminRoleMapper.deleteByUserIdRoleId("1250994414393323520",null);
        System.out.println(i);
    }

}
