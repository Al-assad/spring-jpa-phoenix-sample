package site.assad.jpa.phoenix.conf;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;
import java.util.Properties;

/**
 * phoenix 非池化数据源
 *
 * @author Al-assad
 * @since 2019/10/26
 */
public class PhoenixNoPoolingDataSource extends SimpleDriverDataSource {
    
    public PhoenixNoPoolingDataSource() {
    }
    
    public PhoenixNoPoolingDataSource(Driver driver, String url) {
        super(driver, url);
    }
    
    public PhoenixNoPoolingDataSource(Driver driver, String url, String username, String password) {
        super(driver, url, username, password);
    }
    
    public PhoenixNoPoolingDataSource(Driver driver, String url, Properties conProps) {
        super(driver, url, conProps);
    }
    
    public void setDriverClassName(String driverClassName) {
        try {
            Class<?> driverClass = this.getClass().getClassLoader().loadClass(driverClassName);
            setDriverClass((Class<? extends Driver>) driverClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load class of driverClassName " + driverClassName, e);
        }
    }
    
}
