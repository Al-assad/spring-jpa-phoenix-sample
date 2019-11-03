package site.assad.jpa.sample.embedSample.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * Person 对象 name 属性的级联对象
 *
 * @author yulinying
 * @since 2019-11-02
 */
@Embeddable
public class Name implements Serializable {
    
    private transient static final long serialVersionUID = -8628261077120139351L;
    
    @Column(name = "NAME_FIRST_NAME")
    private String firstName;
    
    @Column(name = "NAME_MIDDLE_NAME")
    private String middleName;
    
    @Column(name = "NAME_LAST_NAME")
    private String lastName;
    
    // 二层内嵌对象
    @Embedded
    private Clan clan;
    
    public Name() {
    }
    
    public Name(String firstName, String middleName, String lastName, Clan clan) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.clan = clan;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Clan getClan() {
        return clan;
    }
    
    public void setClan(Clan clan) {
        this.clan = clan;
    }
    
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", clan=" + clan +
                '}';
    }
}
