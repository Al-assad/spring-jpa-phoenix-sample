package site.assad.jpa.sample.embedSample.entity.rowkey;

import java.io.Serializable;


public class ItemRowkey implements Serializable {
    
    private static final long serialVersionUID = 3403650950215529194L;
    
    private String userId;
    private String key;
    
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
}
