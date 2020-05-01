package burukeyou.im.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/connect")
public class ConnectionServer {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public String getServerInstance() throws UnknownHostException {
        String addr = InetAddress.getLocalHost().getHostAddress();
        return addr;

       /* List<ServiceInstance> instances = discoveryClient.getInstances("im-server");

        List<String> urlList = instances.stream().map(e -> e.getUri().toString()).collect(Collectors.toList());

        // 后期根据不同负载均衡算法后去实例
        int i = ThreadLocalRandom.current().nextInt(instances.size());

        // 从服务注册中心获取一台实例返回
        return urlList.get(i);*/
    }





}
