package site.assad.jpa.sample.embedSample.entity.rowkey;

import java.io.Serializable;

public class OrganizationRowkey implements Serializable {
    
    private static final long serialVersionUID = -5695023538434834623L;
    
    private String userId;
    
    private String orgName;
    
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
