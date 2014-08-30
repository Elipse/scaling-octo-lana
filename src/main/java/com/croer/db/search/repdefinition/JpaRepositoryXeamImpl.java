package com.croer.db.search.repdefinition;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class JpaRepositoryXeamImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaRepositoryXeam<T, ID> {

    private final EntityManager entityManager;

    // There are two constructors to choose from, either can be used.
    public JpaRepositoryXeamImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }

    @Override
    public void refresh(T t) {
        entityManager.refresh(t);
    }
}
