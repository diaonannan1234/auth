package org.example.task.core;

import jakarta.persistence.EntityManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class BaseJpaRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    public BaseJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseDaoFactory(entityManager,applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }

    private static class BaseDaoFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {
        private final EntityManager entityManager;

        private final ApplicationContext applicationContext;

        public BaseDaoFactory(EntityManager entityManager,ApplicationContext applicationContext) {
            super(entityManager);
            this.entityManager = entityManager;
            this.applicationContext = applicationContext;
        }

        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {

           return new JpaSpecRepositoryImpl<T, I>((Class<T>) information.getDomainType(), entityManager, applicationContext);

        }



        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return JpaSpecRepositoryImpl.class;
        }
    }
}