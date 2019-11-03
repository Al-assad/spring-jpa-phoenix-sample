package site.assad.jpa.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtil {
    
    public static Date covertDate(String datetime){
        try {
            return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
