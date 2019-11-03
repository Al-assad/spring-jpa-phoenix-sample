package site.assad.jpa.sample;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.assad.jpa.Application;
import site.assad.jpa.sample.jsonFieldMapSample.entity.FullName;
import site.assad.jpa.sample.jsonFieldMapSample.entity.User;
import site.assad.jpa.sample.jsonFieldMapSample.repository.UserRepository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;

/**
 * site.assad.jpa.sample.jsonFieldMapSample 的 UserRepository 功能测试
 *
 * @author yulinying
 * @since 2019-11-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JsonFieldMapSampleTest {
    
    @Resource
    UserRepository userRepository;
    
    /**
     * 数据储存测试
     */
    @Test
    public void testSave() throws ParseException {
        User user = new User();
        user.setUserId("67c79793-2c68-4494-b36e-d9c51eac5c9b");
        user.setNickName("Bajer");
        user.setLevel(12);
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1990-12-11"));
        // 集合 json 形式储存测试
        user.setOrganization(Arrays.asList("UEA", "ICC", "INTELSAT"));
        // Map json 形式储存测试
        user.setItem(new HashMap<String, String>() {
            {
                put("0b1f53e4675940988541fcd8a74c5cf6", "item1");
                put("119b92e0-bcd1-4c1d-9329-c39505d92888", "item2");
                put("99188562-6768-42a6-adb4-646bad351fcc", "item3");
            }
        });
        // 对象 json 形式储存测试
        user.setFullName(new FullName("Valentine", "VK", "Valentine Hobbes"));
        userRepository.save(user);
    }
    
    /**
     * 查询数据测试
     */
    @Test
    public void testGet() {
        User user = userRepository.findById("67c79793-2c68-4494-b36e-d9c51eac5c9b").orElse(null);
        if (user == null) {
            return;
        }
        System.out.println("userId:" + user.getUserId());
        System.out.println("level:" + user.getLevel());
        System.out.println("nickName:" + user.getNickName());
        System.out.println("birthday:" + user.getBirthday());
        System.out.println("organization:" + String.join(",", user.getOrganization()));
        System.out.println("fullName:" + user.getFullName());
        MapUtils.debugPrint(System.out, "items", user.getItem());
    }
    
}
