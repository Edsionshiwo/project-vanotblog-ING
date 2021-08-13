# 后端部分 Springboot

----

## 介绍
>一个基于 Springboot 开发的简单博客项目。目前为 Demo 版本。

## 开发工具与环境

- IntelliJ IDEA 2021.1 (Ultimate Edition)

- JDK 11 (corretto-11)
- Mysql
- Maven 3 (Version 3.6.3)
- Redis 3.0.504

## 建立本地项目

在完成了上述开发工具与环境的安装与配置以后。

### 建立本地 Maven 项目

通过 IDEA 客户端打开项目根目录下的 `pom.xml` 文件，选择以项目方式打开（ Open as Project）。等待 Maven完成 JAR 依赖下载与导入。

或参考：[(29条消息) 大数据学习笔记——idea如何打开一个别人maven项目_youmianzhou的博客-CSDN博客](https://blog.csdn.net/youmianzhou/article/details/107903138)

### 建立本地 Mysql 数据库

通过 Mysql 可视化操作软件在本地的 Mysql 应用上新建数据库 vanotblog，并通过项目根目录下的 `vanotblog.sql` 文件生成表结构与数据。

也可以通过修改 resource 文件夹下 `application.yml` 中的配置信息来自定义一些数据库的连接参数。若做此修改，须注意主体工程根目录下的 `CodeGenerator` 代码中数据库连接参数也需要做同样的修改。

具体相关操作请自行通过搜索引擎搜索博客教程。

### 建立本地 Redis 数据库

Windows 下的安装与配置参考：[在 windows 上安装 Redis](https://www.redis.com.cn/redis-installation.html)

Linux 下的安装与配置参考：[(29条消息) 【redis】linux上的安装与配置（详细图解）_Jack小强-CSDN博客_redis安装部署linux](https://blog.csdn.net/yjqyyjw/article/details/73293455)

## 启动项目

### 启动 Mysql 服务

在 Windows 服务中启动 Mysql 相关服务。

具体相关操作请自行通过搜索引擎搜索博客教程。

### 启动 Redis 服务

命令行中。

```
redis-server
```

具体相关操作请自行通过搜索引擎搜索博客教程。

### 启动 Springboot 项目

具体相关操作请自行通过搜索引擎搜索博客教程。

## 试试看吧

在浏览器或 Postman 中。

```
http://localhost/hello
```
