package site.assad.jpa.sample.embedSample.entity;

import site.assad.jpa.sample.embedSample.entity.rowkey.OrganizationRowkey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Person 对象 organizations 属性的级联合储存
 * site.assad.jpa.sample.jsonFieldMapSample.entity.User#organization 的另一种储存方式，以空间、查询速度为代价换取 list 结构的可查询性
 *
 * @author yulinying
 * @since 2019-11-02
 */
@Entity
@Table(schema = "TEST", name = "PERSON_ORGANIZATION")
@IdClass(OrganizationRowkey.class)
public class Organization implements Serializable {
    
    private transient static final long serialVersionUID = 7105277586932826395L;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;
    
    @Id
    @Column(name = "ORG_NAME")
    private String orgName;
    
    public Organization() {
    }
    
    public Organization(String userId, String orgName) {
        this.userId = userId;
        this.orgName = orgName;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
