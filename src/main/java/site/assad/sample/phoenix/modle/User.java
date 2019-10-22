package site.assad.sample.phoenix.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 测试数据 PO
 *
 * @author Al-assad
 * @since 2019/10/7
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "ROWKEY")
    private String rowKey;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "AGE")
    private Integer age;
    
    @Column(name = "BIRTHDAY")
    private Date birthday;
    
    public String getRowKey() {
        return rowKey;
    }
    
    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Override
    public String toString() {
        return "UserPO{" +
                "rowKey='" + rowKey + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
