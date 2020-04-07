package burukeyou.search.adapter.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class CanalClient implements DisposableBean {

    private static String IP = "192.168.1.19";

    private static Integer Port = 11111;

    private static String Destination = "burukeyou-user";

    // 连接canal-server的账号密码
    private static String USERNAME = "canal";

    private static String PASSWORD = "canal";

    private CanalConnector connector;


    @Bean
    public CanalConnector canalConnector(){
        InetSocketAddress address = new InetSocketAddress(IP, Port);
        connector = CanalConnectors.newSingleConnector(address, Destination, USERNAME, PASSWORD);
        connector.connect();  //连接
        connector.subscribe(".*\\..*");    // 订阅哪些消息,为空，订阅所有
        connector.rollback(); // 回滚寻找上次中断的位置，继续消费
        return connector;
    }


    // 断开连接
    @Override
    public void destroy() throws Exception {
        if (connector != null)
            connector.disconnect();
    }
}
