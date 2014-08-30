/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.repdefinition;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 *
 * @author elialva
 * @param <R>
 * @param <T>
 * @param <I>
 */
public class JpaRepositoryFactoryBeanXeam<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new JpaRepositoryFactoryXeam(entityManager);
    }

    private static class JpaRepositoryFactoryXeam<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager entityManager;

        public JpaRepositoryFactoryXeam(EntityManager entityManager) {
            super(entityManager);
            System.out.println("Obolo2 " + entityManager);
            this.entityManager = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            System.out.println("Metis " + metadata.getDomainType());
            return new JpaRepositoryXeamImpl<>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return JpaRepositoryXeam.class;
        }
    }
}
