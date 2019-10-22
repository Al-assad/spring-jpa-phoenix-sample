package site.assad.sample.phoenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import site.assad.sample.phoenix.modle.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    
    List<User> findUserByRowKeyBetween(String prefix, String suffix);
    
    @Query("select u from User u where u.age = :age and u.name = :name")
    List<User> findUserByAgeAndName(Integer age, String name);
    
    @Transactional
    @Modifying
    @Query("delete from User where age > :age")
    void deleteByAge(Integer age);
    
    List<User> findUserByRowKeyBetweenAndName(String prefix, String suffix, String name);
}
