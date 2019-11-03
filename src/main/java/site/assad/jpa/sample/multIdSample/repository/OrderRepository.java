package site.assad.jpa.sample.multIdSample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import site.assad.jpa.phoenix.repository.PhoenixJpaRepository;
import site.assad.jpa.sample.multIdSample.entity.Order;
import site.assad.jpa.sample.multIdSample.entity.rowkey.OrderRowkey;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * 实体仓库
 *
 * @author Al-assad
 * @since 2019/10/26
 */
public interface OrderRepository extends PhoenixJpaRepository<Order, OrderRowkey> {
    
    /**
     * 通过部分 rowkey 查询
     */
    List<Order> findByUserIdAndCreateTime(String userId, Date CreateTime);
    
    /**
     * rowkey 范围查询
     */
    List<Order> findByUserIdAndCreateTimeBetween(String userId, Date startTime, Date stopTime);
    
    /**
     * rowkey 范围查询
     */
    List<Order> findByUserIdAndCreateTimeAfter(String userId, Date stopTime);
    
    /**
     * rowkey 范围 + scan 字段条件查询
     */
    List<Order> findByUserIdAndPriceLessThanEqual(String userId, Float priceLimit);
    
    /**
     * in 查询
     */
    List<Order> findByProductNameIn(List<String> products);
    
    /**
     * no in 查询
     */
    List<Order> findByProductNameNotIn(List<String> products);
    
    
    /**
     * 自定义 HQL 查询：查询结果排序
     */
    @Query("select o from Order as o where o.userId = :userId order by o.createTime desc")
    List<Order> findByUserId(String userId);
    
    
    /**
     * 自定义 HQL 查询：查询部分字段
     */
    @Query("select o.productName from Order as o where o.userId = :userId")
    List<String> findProductNameOfUser(String userId);
    
    /**
     * 自定义 SQL 查询：limit 查询
     */
    @Query(value = "select USER_ID,CREATE_TIME,SEQ,PRODUCT_NAME,PRICE from TEST.ORDERS where USER_ID = :userId limit :limit",
            nativeQuery = true)
    List<Order> findByUserIdLimit(String userId, int limit);
    
    
    /**
     * 分页查询：JPQL 实现
     */
    Page<Order> findByUserIdOrderByCreateTimeDesc(String UserId, Pageable pageable);
    
    /**
     * 分页查询：HQL 实现
     */
    @Query("select o from Order as o where o.userId = :userId order by o.createTime desc")
    Page<Order> findByUserId2(String userId, Pageable pageable);
    
    /**
     * 分页查询：SQL 实现
     */
    @Query(value = "select USER_ID,CREATE_TIME,SEQ,PRODUCT_NAME,PRICE from TEST.ORDERS where USER_ID = :userId",
            countQuery = "select count(1) from TEST.ORDERS where USER_ID = :userId",
            nativeQuery = true)
    Page<Order> findByUserId3(String userId, Pageable pageable);
    
    
    /**
     * 删除数据：自定义 HQL
     */
    @Modifying
    @Transactional
    @Query("delete from Order as o where o.userId = :userIds")
    void deleteByUserIds(List<String> userIds);
    
    /**
     * 删除数据：自定义 SQL
     */
    @Modifying
    @Transactional
    @Query(value = "delete from TEST.ORDERS where USER_ID = :userId", nativeQuery = true)
    void deleteByUserId(String userId);
    
    
    /**
     * 强制使用全局索引查询
     */
    @Query(value = "select /*+ INDEX(TEST.ORDERS IDX_PRODUCT_NAME) */ * from test.orders where product_name = :productName", nativeQuery = true)
    List<Order> findByProductName(String productName);
    
    
}
