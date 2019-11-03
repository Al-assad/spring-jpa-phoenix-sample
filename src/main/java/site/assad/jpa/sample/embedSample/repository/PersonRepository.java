package site.assad.jpa.sample.embedSample.repository;


import org.springframework.data.jpa.repository.Query;
import site.assad.jpa.phoenix.repository.PhoenixJpaRepository;
import site.assad.jpa.sample.embedSample.entity.Person;

import java.util.List;

public interface PersonRepository extends PhoenixJpaRepository<Person, String> {
    
    /**
     * list 列表字段查询
     */
    @Query("select p from Person as p left join fetch p.organizations org where p.userId = :userId and org.orgName = :orgName")
    List<Person> findByOrganizationsContains(String userId, String orgName);
    
    
    /**
     * item map字段查询
     */
    @Query("select p from Person as p left join fetch p.items item where p.userId = :userId and item.key = :itemKey and item.value = :itemValue")
    List<Person> findByItemsContains(String userId, String itemKey, String itemValue);
    
}
