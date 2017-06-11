# PiPiShrimpTicketing

## 配置与运行
运行环境 jdk8

运行前修改 src/main/webapp/MET-INF/Hibernate.properties中的jdbc.username,jdbc.password,jdbc.url与本地同步

运行方式 mvn clean jetty:run

## 项目运行预览
![](/doc/pre.gif)

## 前端开发
- 前端部分Release版本路径：/src/main/webapp/WEB-INF/templates
- 详情见[前端部分github地址](https://github.com/SophiaWoo/PiPiShrimp)

## 后端开发

### First Commit
Spring+SpringMVC+Hibernate基本框架的建立，完成配置，实现HelloWorld

### Login and SignIn
实现用户的简单注册登录注销的逻辑，并初步用Shiro进行用户认证

### Add Movie
在数据库中增加Movie实体，并提供了获取电影的API，返回格式为Json供前端解析使用

### Add Cinema
增加Cinema实体并提供了获取Cinema和有关Cinema的条件查找的API</br>
初步设计数据库并实现关联表格CMPos记录Movie在不同Cinema中的座位、场次、时间等重要信息

### Book
实现BookController，完善所有和订票相关的HandlerAdapter

### Modify
修改后端架构，取消视图解析与参数绑定，改成Resful风格的WebService应用

### Test
一些Bug的修复和代码的修改

## 系统分析与设计文档
- 团队文档路径：/doc
- 个人文档成员独自提交

## 团队人员
参见 [项目人员表](https://github.com/xs14331309/PiPiShrimpTicketing/blob/master/doc/t_PiPiShrimp_2_team_profile.pdf)
