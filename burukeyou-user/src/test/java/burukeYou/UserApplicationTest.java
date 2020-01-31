package burukeYou;

import burukeYou.mapper.UmsRoleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserApplicationTest {
    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Test
    public void contextLoads() {
        List<String> roles = umsRoleMapper.findByUserId("102");

        System.out.println();
    }


}
