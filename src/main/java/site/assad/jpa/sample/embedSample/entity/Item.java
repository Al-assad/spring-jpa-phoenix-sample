package site.assad.jpa.sample.embedSample.entity;

import site.assad.jpa.sample.embedSample.entity.rowkey.ItemRowkey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Person 对象 item 属性的级联合储存
 * site.assad.jpa.sample.jsonFieldMapSample.entity.User#item 的另一种储存方式，以空间、查询速度为代价换取 map 结构的可查询性
 * 可以根据实际情况，考虑把 value 属性加入到联合主键，以提高 phoenix 的查询速度（同时会带来更长的 rowkey，但是节省了一个 hbase cell 的储存单位）
 *
 * @author yulinying
 * @since 2019-11-03
 */
@Entity
@Table(schema = "TEST", name = "PERSON_ITEM")
@IdClass(ItemRowkey.class)
public class Item implements Serializable {
    
    private static final long serialVersionUID = 3232368858099023032L;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;
    
    @Id
    @Column(name = "ITEM_KEY")
    private String key;
    
    @Column(name = "ITEM_VALUE")
    private String value;
    
    public Item() {
    }
    
    public Item(String userId, String key, String value) {
        this.userId = userId;
        this.key = key;
        this.value = value;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
