package site.assad.jpa.phoenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * phoenix 默认 JpaRepository 定义，hook 保留
 *
 * @author yulinying
 * @since 2019-10-31
 */
@NoRepositoryBean
public interface PhoenixJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    
    /**
     * Deletes the entity with the given ids.
     */
    void deleteAllById(Iterable<? extends ID> ids);

}
