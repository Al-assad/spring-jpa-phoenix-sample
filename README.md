# spring-jpa-phoenix-sample

Apache Phoenix5.0 通过 Spring Data JPA 的方式集成到 Spring-Boot 的示例代码；  

### 各组件版本

* Spring-Boot： 2.1.5.RELEASE
* Apache Phoenix：5.0.0
* Hadoop：2.7.4.  

### 示例代码说明 

* `Hibernate SQL Dialect` 使用了 https://github.com/jruesga/phoenix-hibernate-dialect  的实现。

* 使用了 `phoenix` 官方文档推荐的非池化数据源连接池。
* 通过 `Hibernate Interceptor` 可以实现对 `Phoenix SQL` 的拦截分析告警拓展。
* 通过继承  `JpaRepository` 实现的 `PhoenixRepository` 基类，用于实现对 `JpaRepository` 行为修改的拓展。

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

* 在不依靠外部数据库、中间件的情况下，`Phoenix` 关于集合、Map、对象数据的储存方式:    
  
**集合、Map 主要有以下 2 种储存思路：**
  
  * 转换 `json` 或其他序列化方法，`phoenix` 保存序列化文本。优点是复杂度低，实体查询速度快，占用体积小，缺点是在 `phoenix` 会丢失掉这些 map、集合的可查询性。
* 分表，创建一对多的级联关系。缺点是增加了额外的复杂度，牺牲了查询性能，增加额外的储存空间（主要为更多的 cell ），不太符合 `HBase` 的冗余式的表设计，优点是这些数据结构化可查询。
  
**对象数据主要有以下 2 种储存思路：**
  
  * 转换 `json` 或其他序列化方法，`phoenix` 保存序列化文本。在不需要对象属性的可检索性的时，比较倾向这种选择。
  * 使用内嵌对象，字段上浮到父表，程序 bean 保持对象逻辑上的嵌套关系。以更多的空间为代价，换取对象字段的可检索性。

