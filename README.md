# spring-jpa-phoenix-sample

Apache Phoenix5.0 通过 Spring Data JPA 的方式集成到 Spring-Boot 的示例代码；

<br>

### 各组件版本

* Spring-Boot： 2.1.5.RELEASE
* Apache Phoenix：5.0.0
* Hadoop：2.7.4

<br>

### 示例代码说明 

* Hibernate SQL Dialect 使用了 https://github.com/jruesga/phoenix-hibernate-dialect  的实现。

* 使用了 phoenix 官方文档推荐的非池化数据源连接池。
* 通过 Hibernate Interceptor 可以实现对 Phoenix SQL 的拦截分析告警拓展。
* 通继承  JpaRepository 实现的 PhoenixRepository 基类，用于实现对 JpaRepository 行为修改的拓展。

* 代码目录说明：

```java
site.assad.jpa
├── phoenix    //phoenix 数据源、实体管理器注册、repository基类、拦截器拓展
│   ├── conf
│   ├── convert
│   ├── interceptor
│   └── repository
└── sample     // 调用示例代码
    ├── embedSample   // 内嵌对象测试、级联对象保存测试
    │   ├── entity
    │   │   └── rowkey
    │   └── repository
    ├── jsonFieldMapSample // json格式保存集合、Map、对象类型数据测试
    │   ├── convert
    │   ├── entity
    │   └── repository
    └── multIdSample // 联合id测试、自增序列、基本CURD、JPQL、HQL、SQL、分页查询、强制索引查询测试
        ├── entity
        │   └── rowkey
        └── repository
```



