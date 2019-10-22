package site.assad.sample.phoenix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.assad.sample.phoenix.modle.User;
import site.assad.sample.phoenix.repository.UserRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PhoenixTest {
    
    @Resource
    UserRepository userRepository;
    
    @Test
    public void test1(){
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }
    
    @Test
    public void test2(){
        User user = userRepository.findById("key233").get();
        System.out.println(user);
    }
    
    @Test
    public void test3(){
        List<User> users = userRepository.findUserByRowKeyBetween("key233", "key240");
        users.forEach(System.out::println);
    }
    
    @Test
    public void test4(){
        List<User> users = userRepository.findUserByAgeAndName(25, "assad");
        users.forEach(System.out::println);
    }
    
    @Test
    public void test5(){
        User user = new User();
        user.setRowKey("key255");
        user.setAge(45);
        user.setBirthday(new Date());
        user.setName("Kilin");
        userRepository.save(user);
    }
    
    
    @Test
    public void test7(){
        userRepository.deleteById("key255");
    }
    
    @Test
    public void test8(){
        userRepository.deleteByAge(40);
    }
    
    @Test
    public void test9(){
        userRepository.findAllById(Arrays.asList("key233", "key2344"));
    }
    
    @Test
    public void test10(){
        List<User> users = userRepository.findUserByRowKeyBetweenAndName("key100", "key233", "assad");
        users.forEach(System.out::println);
    }
    
}
