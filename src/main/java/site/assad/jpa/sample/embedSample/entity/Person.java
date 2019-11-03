package site.assad.jpa.sample.embedSample.entity;

import org.apache.commons.collections4.MapUtils;
import org.apache.hadoop.hbase.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 实体类：普通主键 / 集合/map 数据类型 / 内嵌对象 / 级联对象测试
 *
 * @author yulinying
 * @since 2019-10-31
 */

@Entity
@Table(schema = "TEST", name = "PERSON")
public class Person implements Serializable {
    
    private transient static final long serialVersionUID = -1877323698548334749L;
    
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
    
    // 嵌入对象测试(多层)
    @Embedded
    private Name name;
    
    // 列表对象级联储存
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ElementCollection
    private List<Organization> organizations;
    
    // map对象级联储存
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @MapKeyColumn(name="ITEM_KEY")
    private Map<String, Item> items;
    
    public Person() {
    }
    
    /**
     * 此处实现对 organizations list 数据结构的调用方写入无感
     */
    public void setOrganizationList(List<String> organizationNames) {
        if (CollectionUtils.isEmpty(organizationNames)) {
            return;
        }
        this.organizations = organizationNames.stream().map(name -> new Organization(getUserId(), name)).collect(Collectors.toList());
    }
    
    /**
     * 此处实现对 organizations list 数据结构的调用方读取无感
     */
    public List<String> getOrganizationList() {
        if (CollectionUtils.isEmpty(this.organizations)) {
            return new ArrayList<>(0);
        }
        return this.organizations.stream().map(Organization::getOrgName).collect(Collectors.toList());
    }
    
    /**
     * 此处实现对 items map 数据结构的调用方写入无感
     */
    public void setItemMap(Map<String, String> items) {
        if(MapUtils.isEmpty(this.items)) {
            return;
        }
        Map<String, Item> itemMap = new HashMap<>(this.items.size());
        for(Map.Entry<String, String> e : items.entrySet()) {
            itemMap.put(e.getKey(), new Item(this.getUserId(), e.getKey(), e.getValue()));
        }
        this.items = itemMap;
    }
    
    /**
     * 此处实现对 items map 数据结构的调用方读取无感
     */
    public Map<String, String> getItemMap() {
        if(MapUtils.isEmpty(this.items)) {
            return new HashMap<>(0);
        }
        Map<String, String> itemMap = new HashMap<>(this.items.size());
        for(Map.Entry<String, Item> e : items.entrySet()) {
            itemMap.put(e.getKey(), e.getValue().getValue());
        }
        return itemMap;
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
    
    public Name getName() {
        return name;
    }
    
    public void setName(Name name) {
        this.name = name;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public List<Organization> getOrganizations() {
        return organizations;
    }
    
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
    
    public Map<String, Item> getItems() {
        return items;
    }
    
    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
    
}
