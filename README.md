# 说明
  * IDE: Idea(推荐)、Eclipse、MyEclipse
  * JDK: 1.8
  * 包管理: Maven
  * 主要架框: Spring Cloud、Spring boot、MyBatis、Redis、Swagger2
    
    
# 开发配置(MyEclipse类似) 
  * 1、源码格式要求,导入standard目录下的代码格式文件至IDE中.  
     eclipse添加自定义用户名: 在eclipse.ini 中 -vmargs下面添加一行  -Duser.name=您的名字
  * 2、单元测试插件: EclEmma  
  * 3、代码简化插件: Lombok   
      3-1. 将maven本地包对应的lombok.jar 复制到 eclipse.ini 所在的文件夹目录下   
      3-2. 打开 eclipse.ini ，在最后面插入以下两行并保存.  
           -Xbootclasspath/a:lombok.jar   
           -javaagent:lombok.jar   
      3-3.重启 eclipse
      
      
# 开发注意
* 代码简化请使用Lombok框架提供的注解.
* 用于传输的Dto类如参数不同, 原则上一个接口一个,禁止多个接口共用一个.
* 继承接口的类方法注释通常写到接口方法声明处.
* 同时对多表记录的修改的方法注意加事务处理.
* 对内提供的服务调用,接口部分独立成模块.
* 对内提供的接口,修改必须兼容已发布的,如不能兼容,提供新的接口,过期的接口必须标成@Deprecated.
* IDE编译器给出的Waring,能处理的都必须处理.
* 禁止相同的代码到处拷贝,应提取公用文件中.
* 禁止领域model对象对外提供,对外提供的对象采用DTO。
* 除了简单查询外,其它的一律使用post请求.
* 需要参数检测的,一定要加检测控制.
* 注意各接口权限的控制
* 除了查询可用get外，其它统一使用post。
* 对数据操作的增,删,改操作须加入事务控制。


#文件目录
各个服务代码类似  
采用Maven默认的文件层次结构

* scr/main/java
  * com.imin.XXX - 服务包名
   * advice - 拦截处理
   * conf - 服务配置
   * controller - 控制层
   * dto - 数据传输模型
   * mapper - 数据访问层
   * model - 数据模型
   * remote - 微服务调用
   * service - 服务层 
   * XXXApplication.java - 主程序
* scr/main/resources
  * generator - mybatis生成mapper配置
  * i18n - 多语言配置
  * mybatis - mybatis配置
  * sql - sql语句脚本
  * application.properties - 程序基本配置(spring boot)
  * banner.txt - 个性化启动配置
  * bootstrap.properties - 程序基本配置(spring cloud)
  * logback-spring.xml - 日志配置
* README-VERSION.MD 版本发布记录

       
# 服务说明  

* spring-discovery-service:服务治理中心  
    运行: com.imin.discovery.DiscoveryApplication
    首页: http://127.0.0.1:6100/discovery   
    账号密码: imin/imin123   
    
* spring-example-service: 样例服务  
    运行: com.imin.example.ExampleApplication  
    文档: http://127.0.0.1:6101/example/documentation/swagger-ui.html   
    文档: http://127.0.0.1:6101/example/doc.html   
      
* spring-adminweb-service: 管理后台服务  
    运行: com.imin.adminweb.AdminWebApplication  
    文档: http://127.0.0.1:6103/adminweb/documentation/swagger-ui.html
    
* ~~spring-pay-service: 支付服务~~  
    ~~运行: com.imin.web.WebApplication~~  
    ~~文档: http://127.0.0.1:6104/pay/documentation/swagger-ui.html~~
    
* ~~spring-message-service: 消息服务~~  
    ~~运行: com.imin.message.MessageApplication~~  
    ~~文档: http://127.0.0.1:6105/message/documentation/swagger-ui.html~~
    
* spring-basic-service: 基础服务  
    运行: com.imin.basic.BasicApplication  
    文档: http://127.0.0.1:6106/basic/documentation/swagger-ui.html
    
* spring-config-service: 配置服务  
    运行: com.imin.config.ConfigApplication  
    url: http://127.0.0.1:6107/config/testConfig-dev.properties
    
* ~~spring-user-service: 用户服务(暂时合并到adminweb中)~~  
    ~~运行: com.imin.user.UserApplication~~  
    ~~文档: http://127.0.0.1:6108/user/documentation/swagger-ui.html~~
    
* ~~spring-monitor-service: 监视器服务(暂时弃用，使用FindWeb代替)~~  
    ~~运行: com.imin.monitor.MonitorApplication~~  
    ~~url: http://127.0.0.1:6109/monitor~~   
    ~~账号密码: imin/imin123~~   
    
* ~~spring-storage-service: 存储服务(已迁移到spring-basic-service)~~  
    ~~运行: com.imin.storage.StorageApplication~~  
    ~~文档: http://127.0.0.1:6110/storage/documentation/swagger-ui.html~~   

* FinderWeb日志   
    url: http://127.0.0.1:8080/finder/finder   
    账号密码：admin/1234   
    
    
# 相关资料
  * MyBatis通用MAPPER3: https://mapperhelper.github.io
  * MyBatis分页PageHelper: https://gitee.com/free/Mybatis_PageHelper  


# 其它：
  * 参数验证 JSR 303 - Bean Validation 
  * 接口文档 Swagger2 
  * mvn mybatis-generator:generate 生成mapper文件   
  
  
# spotbugs
  * mvn spotbugs:check
  * mvn spotbugs:gui 
