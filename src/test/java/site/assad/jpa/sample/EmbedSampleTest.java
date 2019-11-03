package site.assad.jpa.sample;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.assad.jpa.Application;
import site.assad.jpa.sample.embedSample.entity.Clan;
import site.assad.jpa.sample.embedSample.entity.Name;
import site.assad.jpa.sample.embedSample.entity.Person;
import site.assad.jpa.sample.embedSample.repository.PersonRepository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * site.assad.jpa.sample.cascadEmbedSample 的 PersonRepository 功能测试
 *
 * @author yulinying
 * @since 2019-11-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmbedSampleTest {
    
    @Resource
    private PersonRepository personRepository;
    
    /**
     * 数据保存测试
     */
    @Test
    public void testSave() throws ParseException {
        Person person = new Person();
        person.setUserId("67c79793-2c68-4494-b36e-d9c51eac5c9b");
        person.setNickName("Bajer");
        person.setLevel(12);
        person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1990-12-11"));
        // 嵌入对象测试
        person.setName(new Name("Valentine", "VK", "Valentine Hobbes", new Clan("House Lannister", "Casterly Rock")));
        // list 级联对象测试
        person.setOrganizationList(Arrays.asList("House Baratheon", "House Tyrell"));
        // map 级联对象测试
        person.setItemMap(new HashMap<String, String>() {{
            put("37d1e759-5ef2-4c86-991e-bc5f5fea9b31", "International Monetary Fund");
            put("d616ef7c-68d8-4304-b349-d8beffa00112", "General Agreement on Tariffs and Trade");
        }});
        personRepository.save(person);
    }
    
    /**
     * 数据查询测试
     */
    @Test
    public void testGet() {
        Person person = personRepository.findById("67c79793-2c68-4494-b36e-d9c51eac5c9b").orElse(null);
        if (person == null) {
            return;
        }
        System.out.println("userId:" + person.getUserId());
        System.out.println("level:" + person.getLevel());
        System.out.println("nickName:" + person.getNickName());
        System.out.println("birthday:" + person.getBirthday());
        System.out.println("name:" + person.getName());
        System.out.println("organization: " + String.join("," + person.getOrganizationList()));
        MapUtils.debugPrint(System.out, "item", person.getItemMap());
        System.out.println(person.getOrganizations().size());
    }
    
    /**
     * 级联 list 对象查询
     */
    @Test
    public void testCascadeSelect() {
        List<Person> persons = personRepository.findByOrganizationsContains("67c79793-2c68-4494-b36e-d9c51eac5c9b", "House Tyrell");
        persons.forEach(p -> {
            System.out.println(p + " organization:" + String.join("," + p.getOrganizationList()));
        });
    }
    
    /**
     * 级联 map 对象查询
     */
    @Test
    public void testCascadeSelect2() {
        List<Person> persons = personRepository.findByItemsContains("67c79793-2c68-4494-b36e-d9c51eac5c9b", "d616ef7c-68d8-4304-b349-d8beffa00112", "General Agreement on Tariffs and Trade");
        persons.forEach(p -> {
            MapUtils.debugPrint(System.out, "item:", p.getItemMap());
        });
    }
    
    
}
