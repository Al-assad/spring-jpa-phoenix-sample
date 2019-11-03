package site.assad.jpa.phoenix.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

/**
 * phoenix 默认 JpaRepository 实现，hook 保留
 *
 * @author yulinying
 * @since 2019-10-31
 */
@SuppressWarnings("ALL")
public class PhoenixJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements PhoenixJpaRepository<T, ID>{
    
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    
    @Autowired
    public PhoenixJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }
    
    /**
     * 覆盖 delete ，阻止查询不到 id 抛出异常
     */
    @Override
    public void deleteById(ID id) {
        Optional<T> entity = super.findById(id);
        entity.ifPresent(this::delete);
    }
    
    
    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        for (ID id : ids) {
            deleteById(id);
        }
    }
    
    public EntityManager getEntityManager() {
        return em;
    }
}
