# ACV-CLOUD
ACV-CLOUD是ACV CHINA基于`Spring 
Cloud`微`服务`化`车控平台`，具有认证授权、车控管理,消息推送等模块，其中包含具备网关API
管理等多个模块，支持多业务系统并行。代码简洁，架构清晰。
核心技术采用`Spring Boot 
2.1.1`以及`Spring Cloud (Finchley.RELEASE)
`相关核心组件，采用`Consul注册中心`，前端采用`-`组件。 

### 连接：[点击打开](https://github.com/dm299770/ACV-CLOUD)

# 图片
![image.png](http://upload-images.jianshu.io/upload_images/5700335-8d69f4e885a4ec85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 架构摘要

#### 服务鉴权
通过`JWT`的方式来加强服务之间调度的权限验证，保证内部服务的安全性。

#### 监控
利用Spring Boot Admin 来监控各个独立Service的运行状态；利用Hystrix Dashboard来实时查看接口的运行状态和调用频率等。

#### 负载均衡
将服务保留的rest进行代理和网关控制，除了平常经常使用的node.js、nginx外，Spring Cloud系列的zuul和ribbon，可以帮我们进行正常的网关管控和负载均衡。其中扩展和借鉴国外项目的扩展基于JWT的`Zuul限流插件`，方面进行限流。

#### 服务注册与调用
基于Eureka来实现的服务注册与调用，在Spring Cloud中使用Feign, 我们可以做到使用HTTP请求远程服务时能与调用本地方法一样的编码体验，开发者完全感知不到这是远程方法，更感知不到这是个HTTP请求。

#### 熔断机制
因为采取了服务的分布，为了避免服务之间的调用“雪崩”，采用了`Hystrix`的作为熔断器，避免了服务之间的“雪崩”。

------

## 功能截图

### 基本功能

## License
Apache License Version 2.0