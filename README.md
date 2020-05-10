
## 涉及技术

|               技术(版本)                |          说明          |                             文档                             |
| :-------------------------------------: | :--------------------: | :----------------------------------------------------------: |
|          **Spring Boot** 2.1.7          | web框架 | [跳转](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/index.html) |
|   **Spring Cloud**   (Greenwich.SR2)    |       微服务框架       | [跳转](https://cloud.spring.io/spring-cloud-static/Greenwich.SR4/multi/multi_spring-cloud.html) |
| **Spring cloud Security Oauth2** |      认证授权框架      | [oauth2](https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/htmlsingle/#boot-features-security-oauth2-authorization-server) [security](https://docs.spring.io/spring-security/site/docs/5.2.1.RELEASE/reference/htmlsingle/) |
|               **MyBatis-Plus**               |        ORM框架         |     [跳转](https://mp.baomidou.com/guide/)      |
|          **Elasticsearch** 6.5          |        搜索框架        | [跳转](https://www.elastic.co/guide/en/elasticsearch/reference/7.1/index.html) |
|              **RabbitMq**               |        消息队列        | [跳转](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/reference/html/boot-features-messaging.html#boot-features-rabbitmq) |
|                **Redis**                |       分布式缓存       | [跳转](https://docs.spring.io/spring-data/redis/docs/current/reference/html/#) |
|              **Netty** 4.1              |      网络通信框架      |           [跳转](https://netty.io/wiki/index.html)           |
|                **Mysql**                |       sql数据库        | [跳转](https://docs.spring.io/spring-data/jpa/docs/2.2.4.RELEASE/reference/html/#preface) |
|               **MongoDB**               |      NoSql数据库       | [跳转](https://docs.spring.io/spring-data/mongodb/docs/2.2.4.RELEASE/reference/html/#preface) |
|              **Canal**               |      数据收集引擎      | [跳转](https://github.com/alibaba/canal/wiki) |
|         **Springfox Swagger2**          |        API文档         | [跳转](https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui) |
|                 **OSS**                 |        对象存储        |  [跳转](https://help.aliyun.com/document_detail/31827.html)  |

## 具体Spring cloud 组件
*	Nacos
*	Zuul
*	Feign
*	Ribbon
*	Hystrix

## 目录结构
*	burukeyou-admin  PC端后台管理服务(RBAC/用户/角色/菜单)
*	burukeyou-api  业务服务
	*	article-center  文章服务
	*	boiling-center 沸点服务
	*	comment-center 评论服务
	*	file-center  OSS文件服务
	*	focus-center  关注服务
	*	like-center 点赞服务
	*	notification-center 通知服务
	*	system-center 系统服务(标签/话题/频道)
	*	user-center 用户服务(收藏/专栏)
*	burukeyou-auth
	*	auth-client   鉴权客户端(SSO客户端)
	*	authentication-server   认证服务(SSO服务端)
* burukeyou-common 通用工具依赖
* burukeyou-gateway 网关服务
* burukeyou-im
	*  好友服务
	* 消息推送服务
* burukeyou-monitor 监控服务
* burukeyou-search
	* 	 search-adapter 数据库同步ES服务(Canal)
	*   search-admin   ES管理服务
	*  search-server  搜索服务





## 业务对接的客户端UI地址
*	[uniapp小程序](https://github.com/burukeYou/burukeyou-mobile/tree/dev)
*	[PC端后台管理](https://github.com/burukeYou/burukeyou-admin/tree/master/burukeyou-admin)

