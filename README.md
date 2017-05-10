# PiPiShrimpTicketing

## 配置与运行
运行环境 jdk8

运行方式 mvn clean jetty:run

运行前修改 Hibernate.properties中的jdbc.username,jdbc.password,jdbc.url与本地同步

## 开发过程

### First Commit
Spring+SpringMVC+Hibernate基本框架的建立，完成配置，实现HelloWorld

### Login and SignIn
实现用户的简单注册登录注销的逻辑，并初步用Shiro进行用户认证

### Add Movie
在数据库中增加Movie实体，并提供了获取电影的API，返回格式为Json供前端解析使用
