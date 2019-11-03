package site.assad.jpa.sample.multIdSample.entity.rowkey;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实体类：复合主键测试
 *
 * @author yulinying
 * @since 2019-10-28
 */
public class OrderRowkey implements Serializable {
    
    private transient static final long serialVersionUID = -6450174474310815768L;
    
    private String userId;
    
    private Date createTime;
    
    private int seq;
    
    public OrderRowkey() {
    }
    
    // 以下代码由 lombok 自动生成
    OrderRowkey(String userId, Date createTime, int seq) {
        this.userId = userId;
        this.createTime = createTime;
        this.seq = seq;
    }
    
    public static OrderRowKeyBuilder builder() {
        return new OrderRowKeyBuilder();
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
    
    @Override
    public String toString() {
        return "OrderRowKey{" +
                "userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", seq=" + seq +
                '}';
    }
    
    public static class OrderRowKeyBuilder {
        private String userId;
        private Date createTime;
        private int seq;
        
        OrderRowKeyBuilder() {
        }
        
        public OrderRowKeyBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
    
        public OrderRowKeyBuilder createTime(String createTime) {
            try {
                this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }
    
        public OrderRowKeyBuilder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }
        
        public OrderRowKeyBuilder seq(int seq) {
            this.seq = seq;
            return this;
        }
        
        public OrderRowkey build() {
            return new OrderRowkey(userId, createTime, seq);
        }
        
    }
}
