## burukeyou是一个微服务下的社区交流安全搜索系统,专注于


- 微服务
- 社区交流
- 安全
- 搜索


# 二  技术栈

## 后端技术

|               技术(版本)                |          说明          |                             文档                             |
| :-------------------------------------: | :--------------------: | :----------------------------------------------------------: |
|          **Spring Boot** 2.1.7          | spring+ spring mvc框架 | [跳转](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/index.html) |
|   **Spring Cloud**   (Greenwich.SR2)    |       微服务框架       | [跳转](https://cloud.spring.io/spring-cloud-static/Greenwich.SR4/multi/multi_spring-cloud.html) |
| **Spring cloud Security** +  **Oauth2** |      认证授权框架      | [oauth2](https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/htmlsingle/#boot-features-security-oauth2-authorization-server) [security](https://docs.spring.io/spring-security/site/docs/5.2.1.RELEASE/reference/htmlsingle/) |
|               **MyBatis PLUS**               |        ORM框架         |     [跳转](https://mybatis.org/mybatis-3/zh/index.html)      |
|          **Elasticsearch** 7.1          |        搜索引擎        | [跳转](https://www.elastic.co/guide/en/elasticsearch/reference/7.1/index.html) |
|              **RabbitMq**               |        消息队列        | [跳转](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/boot-features-messaging.html#boot-features-rabbitmq) |
|                **Redis**                |       分布式缓存       | [跳转](https://docs.spring.io/spring-data/redis/docs/current/reference/html/#) |
|              **Netty** 4.1              |      网络通信框架      |           [跳转](https://netty.io/wiki/index.html)           |
|                **mysql**                |       sql数据库        | [跳转](https://docs.spring.io/spring-data/jpa/docs/2.2.4.RELEASE/reference/html/#preface) |
|               **MongoDb**               |      NoSql数据库       | [跳转](https://docs.spring.io/spring-data/mongodb/docs/2.2.4.RELEASE/reference/html/#preface) |
|              **LogStash**               |      数据收集引擎      | [跳转](https://www.elastic.co/guide/en/logstash/7.x/index.html) |
|         **Springfox Swagger2**          |        API文档         | [跳转](https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui) |
|                 **OSS**                 |        对象存储        |  [跳转](https://help.aliyun.com/document_detail/31827.html)  |
|              **Easy POI**               |  操作excel, word,csv   | [跳转](https://opensource.afterturn.cn/doc/easypoi.html#102) |



<font size='5' color='blue'>具体Spring Cloud技术选型:</font>

spring cloud 中文网

- https://www.springcloud.cc/



选型总结:

- 服务注册和发现和配置中心: nacos
- 服务网关: gateway
- 服务调用:  feign 和 openFeign
- 容错保护和限流:  hystrix 和 Sentinel
- 状态监控: spring boot admin
- 消息总线: SpringCloud Bus+Rabbitmq





<font size='4' color='red'>1. 注册中心: nacos</font>

[nacos官方文档](https://nacos.io/zh-cn/docs/what-is-nacos.html)

注册中心对比:

|                   |           Nacos            |   Eureka    |      Consul       | Zookeeper  |
| :---------------: | :------------------------: | :---------: | :---------------: | :--------: |
|    一致性协议     |          CP + AP           |     AP      |        CP         |     CP     |
|     健康检查      | TCP/HTTP/MYSQL/Client Beat | Client Beat | TCP/HTTP/gRPC/Cmd | Keep Alive |
|   负载均衡策略    |   权重/metadata/Selector   |   Ribbon    |       Fabio       |     -      |
|     雪崩保护      |             有             |     有      |        无         |            |
|   自动注销实例    |            支持            |    支持     |      不支持       |    支持    |
|     访问协议      |          HTTP/DNS          |    HTTP     |     HTTP/DNS      |    TCP     |
|     监听支持      |            支持            |    支持     |       支持        |    支持    |
|    多数据中心     |            支持            |    支持     |       支持        |   不支持   |
|  跨注册中心同步   |            支持            |   不支持    |       支持        |   不支持   |
| 与SpringCloud集成 |            支持            |    支持     |       支持        |   不支持   |
|    与Dubbo集成    |            支持            |   不支持    |      不支持       |    支持    |
|     与K8S集成     |            支持            |   不支持    |       支持        |   不支持   |



配置中心集成对比:

**功能差异**

| 模块     | Nacos | Eureka | 说明                                                         |
| :------- | :---- | :----- | :----------------------------------------------------------- |
| 注册中心 | 是    | 是     | 服务治理基本功能，负责服务中心化注册                         |
| 配置中心 | 是    | `否`   | Eureka需要配合Config实现配置中心，且不提供管理界面           |
| 动态刷新 | 是    | `否`   | Eureka需要配合MQ实现配置动态刷新，Nacos采用Netty保持TCP长连接实时推送 |
| 可用区AZ | 是    | 是     | 对服务集群划分不同区域，实现区域隔离，并提供容灾自动切换     |
| 分组     | 是    | `否`   | Nacos可用根据业务和环境进行分组管理                          |
| 元数据   | 是    | 是     | 提供服务标签数据，例如环境或服务标识                         |
| 权重     | 是    | `否`   | Nacos默认提供权重设置功能，调整承载流量压力                  |
| 健康检查 | 是    | 是     | Nacos支持由客户端或服务端发起的健康检查，Eureka是由客户端发起心跳 |
| 负载均衡 | 是    | 是     | 均提供负责均衡策略，Eureka采用Ribion                         |
| 管理界面 | 是    | `否`   | Nacos支持对服务在线管理，Eureka只是预览服务状态              |



**部署安装**

| 模块     | Nacos    | Eureka                  | 说明                                   |
| :------- | :------- | :---------------------- | :------------------------------------- |
| MySql    | 是       | `否`                    | Nacos需要采用MySql进行数据进行持久化   |
| MQ       | `否`     | 是                      | Eureka需要采用MQ进行配置中心刷新       |
| 配置中心 | 是       | `否`                    | Eureka结合Config或者Consul实现配置中心 |
| 配置文件 | 在线编辑 | 本地文件或者Git远程文件 | Eureka结合Config或者Consul             |
| 集群     | 是       | 是                      | Nacos需要配置集群ip再启动              |





总结:

- 选用nacos,功能更完善,文档更齐全,支持配置中心及其动态刷新,版本还在迭代中
- 而Eureka需要结合Config或者Consul才能实现配置中心,还需要配合MQ才能实现配置文件动态刷新,版本也停止了迭代





<font size='4' color='red'>2. 网关:  Gateway</font>

[官方文档](https://cloud.tencent.com/developer/article/1403887)

|                        | Spring cloud Gateway |  ZUUL 1.0   | Nginx | Linkerd |
| ---------------------- | :------------------: | :---------: | :---: | :-----: |
| 架构                   |      基于netty       | 基于servlet |       |         |
| 运行方式               |      异步非阻塞      |  同步阻塞   |       |         |
| Spring Cloud集成       |         支持         |    支持     |       |         |
| 支持长连接、Web Socket |         支持         |   不支持    |       |         |
| 限流、限速             |         支持         |   第三方    |       |         |
| 监控                   |         支持         |    支持     |       |         |
| 上手难度               |         中等         |    简单     |       |         |
|                        |                      |             |       |         |
|                        |                      |             |       |         |



Spring Cloud Gateway 具有如下特性：

- 基于Spring Framework 5, Project Reactor 和 Spring Boot 2.0 进行构建；
- 动态路由：能够匹配任何请求属性；
- 可以对路由指定 Predicate（断言）和 Filter（过滤器）；
- 集成Hystrix的断路器功能；
- 集成 Spring Cloud 服务发现功能；
- 易于编写的 Predicate（断言）和 Filter（过滤器）；
- 请求限流功能；
- 支持路径重写。









<font size='4' color='red'>3. 服务调用</font>

feign 和 openFeign







<font size='4' color='red'>4. 容错处理: hystrix</font>

 







<font size='4' color='red'>5. 分布式配置中心</font>

Spring cloud config 和 Nacos









<font size='4' color='red'>6. 流量防卫兵: Sentinel</font>

[中文文档](https://github.com/alibaba/Sentinel/wiki/介绍)







|                |                    Sentinel                    |            Hystrix            |
| :------------: | :--------------------------------------------: | :---------------------------: |
|    隔离策略    |                   基于并发数                   |     线程池隔离/信号量隔离     |
|  熔断降级策略  |             基于响应时间或失败比率             |         基于失败比率          |
|  实时指标实现  |                    滑动窗口                    |    滑动窗口（基于 RxJava）    |
|    规则配置    |                 支持多种数据源                 |        支持多种数据源         |
|     扩展性     |                   多个扩展点                   |          插件的形式           |
| 基于注解的支持 |                    即将发布                    |             支持              |
|  调用链路信息  |                  支持同步调用                  |            不支持             |
|      限流      |   基于 QPS / 并发数，支持基于调用关系的限流    |            不支持             |
|    流量整形    |             支持慢启动、匀速器模式             |            不支持             |
|  系统负载保护  |                      支持                      |            不支持             |
|  实时监控 API  |                    各式各样                    |           较为简单            |
|     控制台     | 开箱即用，可配置规则、查看秒级监控、机器发现等 |            不完善             |
| 常见框架的适配 |     Servlet、Spring Cloud、Dubbo、gRPC 等      | Servlet、Spring Cloud Netflix |













## 前端技术

|    技术     |             说明             |                             文档                             |
| :---------: | :--------------------------: | :----------------------------------------------------------: |
|     vue     |            js框架            |            [跳转](https://cn.vuejs.org/v2/guide/)            |
| Vue-router  |           路由框架           |             [跳转](https://router.vuejs.org/zh/)             |
|    Vuex     |       全局状态管理框架       |              [跳转](https://vuex.vuejs.org/zh/)              |
|    Axios    |        构建HTTP 请求         |      [跳转](https://www.kancloud.cn/yunye/axios/234845)      |
| Element UI  |            UI框架            | [跳转](https://element.eleme.io/#/zh-CN/component/installation) |
|   Lodash    |           Js工具库           |              [跳转](https://www.lodashjs.com/)               |
| vue-cookies | Vue.js 插件操作浏览器cookies |      [跳转](https://www.npmjs.com/package/vue-cookies)       |
|  v-charts   |           图表插件           |              [跳转](https://v-charts.js.org/#/)              |
|    Vant     |      移动端 Vue 组件库       |        [跳转](https://youzan.github.io/vant/#/zh-CN/)        |
| animate.css |          css动画库           |        [跳转](https://daneden.github.io/animate.css/)        |
|   jQuery    |       JavaScript函数库       |             [跳转](http://jquery.cuishifeng.cn/)             |
|  websocket  |        全双工通讯协议        |   [跳转](https://www.runoob.com/html/html5-websocket.html)   |
|             |                              |                                                              |







## 运维部署

|   技术    |       说明        |
| :-------: | :---------------: |
|  Docker   | 微服务容器化部署  |
|  Jenkins  |    自动化部署     |
|   nginx   | 反向代理,负载均衡 |
| Portainer |  可视化容器管理   |





## 开发工具环境

|     工具      |           说明            |
| :-----------: | :-----------------------: |
|   JDK  1.8    |       java环境支持        |
| intellij idea |       开发后端的IDE       |
|    Navicat    |      数据库连接工具       |
|   ProcessOn   |      流程图绘制工具       |
|  FinalShell   |     Linux远程连接工具     |
|   Webstorm    |       开发前端的IDE       |
|      git      |       版本控制工具        |
|      rdm      |   redis图形界面连接工具   |
|    Kibana     | Elasticsearch连接监控工具 |
|     maven     |       项目管理工具        |
|     linux     |         部署环境          |

