# SimpleFrame

SpringBoot Bussiness Frame

这是一个通用SpringBoot 项目框架， 集成了很多常用的通用的依赖并且做好了配置

真正的只需关心业务，无需折腾项目结构的好框架

本项目是作者总结这多年的开发经验所得，花费了较多精力整理

如果你觉得好用，请务必给个 <h1> STAR</h1>





# 项目简单介绍


![Spring Boot 2.1.4.RELEASE](https://img.shields.io/badge/Spring%20Boot-2.1.3.RELEASE-brightgreen.svg)
![Mysql 5.6](https://img.shields.io/badge/Mysql-5.6-blue.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.6.0-yellowgreen.svg)
![license](https://img.shields.io/badge/license-MPL--2.0-blue.svg)  


## 更新日志
20200804  更新springboot版本：2.1.4 ===> 2.2.6
          同步调整flyway版本至 5.2.4


## 项目结构


通读项目结构，可以看出

本项目基于 MAVEN 多模块

之所以创建多模块，是为了更好的划分层次，分辨出通用类型代码，和业务类型代码

frame-common  通用模块，用于放置公共的一些东西，比如认证，字符处理，redis，excel处理等等

frame-service 业务模块，真正可运行的业务模块，引入了frame-common模块，同时实现自己的业务逻辑，当然

可以继续扩展业务模块，可以继续创建frame-service2，3…………等等，具体看自己的需要！



## 项目集成了哪些依赖


本项目引入了很多常用的依赖，组件，如：

- redis
- druid
- swagger
- 通用mapper
- mybatis
- lombok
- aop切面
- aliyun-sdk-oss
- easyexcel
- flyway数据库更新管理

- …………

- 等等

更多细节请查看pom.xml，不满足的自行集成



## 项目实现了哪些功能


同时本项目，实现了大多项目的通用需求，如：

- 权限认证
- 接口自动文档
- 数据库监控
- 防重复提交
- Excel导入导出（反射实体）
- 分布式定时任务
- 数据验证
- 全局异常/业务异常捕获处理
- 通用分页
- 分布式全局唯一ID生成（雪花算法）
- 通用redis工具类
- 全局请求拦截器处理
- mybatis生成实体/mapper工具

- …………

- 等等




## Demo介绍


项目上，作者写几个简单的使用案例，方便初学SpringBoot的朋友入门， 

当然，你也可以关注我的另一个SpringBoot项目 

"从零开始学SpringBoot"，通过一个个单独的小Demo来学习

https://github.com/MrCoderStack/SpringBootDemo.git

关注作者的朋友可能知道，

作者也是从其它语言转JAVA，一路学习，踩坑过来的，这个项目记录了

作者的成长过程，我相信适合大多数的朋友～

另外，作者还有个SpringBoot的多模块项目框架，

https://github.com/MrCoderStack/SpringBootFrame

当然了，那个框架现在看来，其实不大实用，

虽然模块划分的较为细致，但是，作者几年下来，发现，落地的项目更多的还是使用了本项目的这种结构层次！

当然，对于关心层次的朋友还是有参考价值的，同时该项目还集成了分布式事务解决方案，可以学习下～

弱弱的解释下，对于本项目没有集成分布式事务，原因在于，其实业务逻辑上完全可以规避这种场景，没必要为了使用而
使用～ 相信更多的朋友所在的项目完全没有这种使用场景～


## 寄言

后续，作者会不断补充更多的功能，总之，集成进的都是JAVA项目正常需要的功能，个性化自定义的
一些需要大家自己扩展～





