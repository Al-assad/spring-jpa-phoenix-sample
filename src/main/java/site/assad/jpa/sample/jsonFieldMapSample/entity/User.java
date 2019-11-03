package site.assad.jpa.sample.jsonFieldMapSample.entity;


import com.fasterxml.jackson.databind.SequenceWriter;
import site.assad.jpa.phoenix.convert.GenericJsonConverter;
import site.assad.jpa.sample.jsonFieldMapSample.convert.FullNameJsonConverter;
import site.assad.jpa.sample.jsonFieldMapSample.entity.FullName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实体类：普通主键 / 集合/map 数据类型 / 子对象测试
 * @author yulinying
 * @since 2019-10-31
 */

@Entity
@Table(schema = "TEST", name = "USER")
public class User implements Serializable {
    
    private transient static final long serialVersionUID = 910934668276394399L;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;
    
    @Column(name = "NICK_NAME")
    private String nickName;
    
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column(name = "LEVEL")
    private Integer level = 0;
    
    // json 方式储存集合数据
    @Column(name = "ORGANIZATION")
    @Convert(converter = GenericJsonConverter.class)
    private List<String> organization;
    
    // json 方式储存 map
    @Column(name = "ITEM")
    @Convert(converter = GenericJsonConverter.class)
    private Map<String, String> item;
    
    // json 方式储存对象
    @Column(name = "FULL_NAME")
    @Convert(converter = FullNameJsonConverter.class)
    private FullName fullName;
    
    public User() {
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public List<String> getOrganization() {
        return organization;
    }
    
    public void setOrganization(List<String> organization) {
        this.organization = organization;
    }
    
    public Map<String, String> getItem() {
        return item;
    }
    
    public void setItem(Map<String, String> item) {
        this.item = item;
    }
    
    public FullName getFullName() {
        return fullName;
    }
    
    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
   
}
