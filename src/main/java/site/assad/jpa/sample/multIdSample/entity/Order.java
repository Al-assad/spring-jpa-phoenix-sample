package site.assad.jpa.sample.multIdSample.entity;


import site.assad.jpa.sample.multIdSample.entity.rowkey.OrderRowkey;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试数据实体 - 复合主键
 *
 * @author Al-assad
 * @since 2019/10/7
 */
@Entity
@Table(schema = "TEST", name = "ORDERS")
@IdClass(OrderRowkey.class)
public class Order implements Serializable {
    
    private transient static final long serialVersionUID = -2516313704077627476L;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;
    
    @Id
    @Column(name = "CREATE_TIME")
    private Date createTime;
    
    @Id
    @GeneratedValue(generator = "seq-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq-generator", schema = "TEST", sequenceName = "ORDER_SEQ", allocationSize = 10)
    @Column(name = "SEQ")
    private int seq;
    
    @Column(name = "PRODUCT_NAME")
    private String productName;
    
    @Column(name = "PRICE")
    private Float price;
    
    public Order() {
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public int getSeq() {
        return seq;
    }
    
    public void setSeq(int seq) {
        this.seq = seq;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Float getPrice() {
        return price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
    
    // 以下代码由 lombok 自动生成
    Order(String userId, Date createTime, int seq, String productName, Float price) {
        this.userId = userId;
        this.createTime = createTime;
        this.seq = seq;
        this.productName = productName;
        this.price = price;
    }
    
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", seq=" + seq +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
    
    public static class OrderBuilder {
        private String userId;
        private Date createTime;
        private int seq;
        private String productName;
        private Float price;
        
        OrderBuilder() {
        }
        
        public OrderBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
        
        public OrderBuilder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
    
        public OrderBuilder createTime(String createTime) {
            try {
                this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }
        
        public OrderBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }
        
        public OrderBuilder price(Float price) {
            this.price = price;
            return this;
        }
        
        public Order build() {
            return new Order(userId, createTime, seq, productName, price);
        }
    }
}
