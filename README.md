# morphia-play-injector [![Build Status](http://ci2.lvxingpai.com/buildStatus/icon?job=MorphiaPlayInjector)](http://ci2.lvxingpai.com/job/MorphiaPlayInjector/)

通过依赖注入的方式，将Morphia Datastore注入Play Framework工程之中。在Play Framework 2.4.x的版本测试通过。

## 安装

在`build.sbt`中加入：

```sbt
"com.lvxingpai" %% "morphia-play-injector" % "0.1.1"
```

注意：安装此artifact需要有[Lvxingpai sbt Repository](http://nexus.lvxingpai.com)的访问权限。

## 使用方法

### `application.conf`配置

首先需要将本模块引入依赖注入系统中。在`application.conf`中添加：

```
play.modules.enabled += "com.lvxingpai.inject.morphia.MorphiaModule"
```

然后，需要设置好相应的MongoDB连接信息：

```
mongo {
  braavos {
    host: 192.168.100.2
    port: 32001
    user: username
    password: your_password
    database: the_database
  }
}
```

上面这个配置，就定义了一个叫做`braavos`的`Datastore`注入点。

### 依赖注入

具体注入代码示例如下：

```scala
class Application @Inject() (@Named("braavos") ds: Datastore) extends Controller {
  def index = Action {
    Ok(views.html.index("Your data store is ready."))
  }
}
```

mongo配置文件中的选项详情，请参见[morphia-factory](https://github.com/Lvxingpai/morphia-factory)

## 构建状态

[![Build Status](http://ci2.lvxingpai.com/buildStatus/icon?job=MorphiaPlayInjector)](http://ci2.lvxingpai.com/job/MorphiaPlayInjector/)
