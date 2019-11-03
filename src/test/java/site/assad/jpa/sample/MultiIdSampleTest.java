package site.assad.jpa.sample;

import org.apache.hadoop.hbase.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import site.assad.jpa.Application;
import site.assad.jpa.sample.multIdSample.entity.Order;
import site.assad.jpa.sample.multIdSample.entity.rowkey.OrderRowkey;
import site.assad.jpa.sample.multIdSample.repository.OrderRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static site.assad.jpa.sample.TestUtil.covertDate;


/**
 * Order 对象持久层测试
 *
 * @author yulinying
 * @since 2019-10-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MultiIdSampleTest {
    
    @Resource
    OrderRepository orderRepository;
    
    /* ---------------------------- 插入测试 ------------------------------- */
    // 插入测试: 单条数据插入
    @Test
    public void testInsert() {
        //
        Order order = new Order();
        order.setUserId("701578d9-6077-4f03-8ca5-a83c3b1bf2df");
        order.setCreateTime(new Date());
        order.setProductName("Break with a Banshee");
        order.setPrice(34.0f);
        orderRepository.save(order);
    }
    
    // 插入测试：批量插入
    @Test
    public void testInsert2() {
        List<Order> orders = Arrays.asList(
                Order.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-11 03:50:11")
                        .productName("Numerology and Grammatica")
                        .price(99.5f)
                        .build(),
                Order.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-20 03:00:11")
                        .productName("New Theory of Numerology")
                        .price(92.3f)
                        .build(),
                Order.builder()
                        .userId("267068c3-b404-41c0-a8d2-6472e06e190b")
                        .createTime("2019-10-20 11:10:00")
                        .productName("Gadding with Ghouls")
                        .price(30.0f)
                        .build()
        );
        orderRepository.saveAll(orders);
    }
    
    
    /* ---------------------------- 查询测试 -------------------------------*/
    // 查询测试： 通过 id 查询
    @Test
    public void testSelect() {
        OrderRowkey rowkey = OrderRowkey.builder()
                .userId("c411e430-69c2-420b-af80-b63776377fb7")
                .createTime("2019-09-20 03:00:11")
                .seq(14)
                .build();
        Order order = orderRepository.findById(rowkey).orElse(null);
        System.out.println(order);
    }
    
    // 查询测试：通过批量 id 查询
    @Test
    public void testSelect2() {
        List<OrderRowkey> rowkeys = Arrays.asList(
                OrderRowkey.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-20 03:00:11")
                        .seq(14)
                        .build(),
                OrderRowkey.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-11 03:50:11")
                        .seq(13)
                        .build()
        );
        List<Order> orders = orderRepository.findAllById(rowkeys);
        orders.forEach(System.out::println);
    }
    
    // 查询测试：Jpa 自动命名方法
    @Test
    public void testSelect3() {
        // 通过部分 rowkey 查询
        List<Order> orders1 = orderRepository.findByUserIdAndCreateTime("c411e430-69c2-420b-af80-b63776377fb7", covertDate("2019-09-20 03:00:11"));
        orders1.forEach(System.out::println);
        
        // 通过部分 rowkey 进行范围查询
        List<Order> orders2 = orderRepository.findByUserIdAndCreateTimeBetween("c411e430-69c2-420b-af80-b63776377fb7", covertDate("2019-09-10 00:00:00"), covertDate("2019-09-12 23:59:59"));
        orders2.forEach(System.out::println);
        
        // 通过部分 rowkey 进行范围查询
        List<Order> orders3 = orderRepository.findByUserIdAndCreateTimeAfter("c411e430-69c2-420b-af80-b63776377fb7", covertDate("2019-09-19 00:00:00"));
        orders3.forEach(System.out::println);
        
        // 通过 rowkey + scan 字段条件查询
        List<Order> orders4 = orderRepository.findByUserIdAndPriceLessThanEqual("c411e430-69c2-420b-af80-b63776377fb7", 100.0f);
        orders4.forEach(System.out::println);
        
        // in 查询
        List<Order> orders5 = orderRepository.findByProductNameIn(Arrays.asList("Numerology and Grammatica", "Sonnets of a Sorcerer"));
        orders5.forEach(System.out::println);
        
        // no in 查询
        List<Order> orders6 = orderRepository.findByProductNameNotIn(Arrays.asList("Numerology and Grammatica", "Sonnets of a Sorcerer"));
        orders6.forEach(System.out::println);
    }
    
    /**
     * 查询测试：自定义 HQL/SQL 查询
     */
    @Test
    public void testSelect4() {
        // 自定义 HQL 查询： 结果排序
        List<Order> orders = orderRepository.findByUserId("c411e430-69c2-420b-af80-b63776377fb7");
        orders.forEach(System.out::println);
        
        // 自定义 HQL 查询：查询部分字段
        List<String> productNames = orderRepository.findProductNameOfUser("c411e430-69c2-420b-af80-b63776377fb7");
        productNames.forEach(System.out::println);
        
        // 自定义 SQL 查询：limit 查询
        List<Order> orders2 = orderRepository.findByUserIdLimit("c411e430-69c2-420b-af80-b63776377fb7", 10);
        orders2.forEach(System.out::println);
    }
    
    /**
     * 查询测试：分页查询
     */
    @Test
    public void testSelect5() {
        // 使用 JPQL
        Page<Order> pager1 = orderRepository.findByUserIdOrderByCreateTimeDesc("c411e430-69c2-420b-af80-b63776377fb7", PageRequest.of(0, 5));
        System.out.println("total pages:" + pager1.getTotalPages());
        System.out.println("total elements: " + pager1.getTotalElements());
        List<Order> orders1 = pager1.getContent();
        orders1.forEach(System.out::println);
        
        // 使用自定义 HQL
        Page<Order> pager2 = orderRepository.findByUserId2("c411e430-69c2-420b-af80-b63776377fb7", PageRequest.of(1, 5));
        List<Order> orders2 = pager2.get().collect(Collectors.toList());
        orders2.forEach(System.out::println);
        
        // 使用自定义 SQL
        Page<Order> pager3 = orderRepository.findByUserId2("c411e430-69c2-420b-af80-b63776377fb7", PageRequest.of(0, 4));
        List<Order> orders3 = pager3.get().collect(Collectors.toList());
        orders3.forEach(System.out::println);
    }
    
    
    /* ---------------------------- 更新测试 -------------------------------*/
    
    /**
     * 更新测试：更新单条记录
     */
    @Test
    public void testUpdate1() {
        OrderRowkey rowkey = OrderRowkey.builder()
                .userId("c411e430-69c2-420b-af80-b63776377fb7")
                .createTime("2019-09-20 03:00:11")
                .seq(14)
                .build();
        Order order = orderRepository.findById(rowkey).orElse(null);
        if (order == null) {
            return;
        }
        order.setPrice(112.2f);
        orderRepository.save(order);
    }
    
    /**
     * 更新测试：更新多条记录
     */
    @Test
    public void testUpdate2() {
        List<Order> orders = orderRepository.findAll();
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        orders.forEach(o -> o.setPrice(o.getPrice() + 1));
        orderRepository.saveAll(orders);
    }
    
    
    /* ---------------------------- 删除测试 -------------------------------*/
    
    /**
     * 删除测试：单条记录删除
     */
    @Test
    public void testDelete() {
        
        OrderRowkey rowkey = OrderRowkey.builder()
                .userId("c411e430-69c2-420b-af80-b63776377fb7")
                .createTime("2019-09-20 03:00:11")
                .seq(14)
                .build();
        
        Order order = orderRepository.findById(rowkey).orElse(null);
        if (order == null) {
            return;
        }
        // 通过实体删除
        orderRepository.delete(order);
        // 通过 id 删除
        orderRepository.deleteById(rowkey);
    }
    
    /**
     * 删除测试：批量删除
     */
    @Test
    public void testDelete2() {
        List<OrderRowkey> rowkeys = Arrays.asList(
                OrderRowkey.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-20 03:00:11")
                        .seq(14)
                        .build(),
                OrderRowkey.builder()
                        .userId("c411e430-69c2-420b-af80-b63776377fb7")
                        .createTime("2019-09-20 03:00:11")
                        .seq(12)
                        .build()
        );
        List<Order> orders = orderRepository.findAllById(rowkeys);
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        // 通过实体删除
        orderRepository.deleteAll(orders);
        // 通过 id 删除
        orderRepository.deleteAllById(rowkeys);
    }
    
    /**
     * 删除测试：自定义 HQL/SQL 语句删除
     */
    @Test
    public void testDelete3() {
        // 自定义 HQL 删除
        orderRepository.deleteByUserIds(Arrays.asList("701578d9-6077-4f03-8ca5-a83c3b1bf2df"));
        // 指定 SQL 删除
        orderRepository.deleteByUserId("267068c3-b404-41c0-a8d2-6472e06e190b");
    }
    
    
    /* ---------------------------- 其他特性测试 -------------------------------*/
    
    /**
     * 强制全局二级索引查询测试
     */
    @Test
    public void testSecondIndex(){
        List<Order> orders = orderRepository.findByProductName("Hairy Snout, Human Heart");
        orders.forEach(System.out::println);
    }
    
}
