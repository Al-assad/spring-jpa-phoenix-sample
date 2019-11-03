package site.assad.jpa.sample.embedSample.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Name 对象 clan 属性的级联对象
 *
 * @author yulinying
 * @since 2019-11-02
 */
@Embeddable
public class Clan implements Serializable {
    
    private transient static final long serialVersionUID = 2709392730842746934L;
    
    @Column(name = "NAME_CLAN_NAME")
    private String name;
    
    @Column(name = "NAME_CLAN_AREA")
    private String area;
    
    public Clan() {
    }
    
    public Clan(String name, String area) {
        this.name = name;
        this.area = area;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public String toString() {
        return "Clan{" +
                "name='" + name + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
