package org.example.task.core;

import jakarta.persistence.Access;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import org.casbin.jcasbin.main.Enforcer;
import org.example.task.TaskEntity;
import org.example.task.policy.Act;
import org.example.task.policy.PolicyAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class JpaSpecRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements JpaSpecRepository<T,ID> {
    private EntityManager entityManager;
    private JpaEntityInformation<T, ?> entityInformation;


    private ApplicationContext context;

    public JpaSpecRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public JpaSpecRepositoryImpl(Class<T> domainClass, EntityManager entityManager, ApplicationContext context) {
        this(domainClass,entityManager);
        this.context = context;
    }

    public JpaSpecRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.entityInformation = entityInformation;
        this.entityManager = em;
    }

    @Override
    public Optional<T> findById(ID id) {
        return super.findById(id);
    }

    @Override
    public List<T> findAll() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        String sub = (String)ra.getAttribute("auth_user_ame",RequestAttributes.SCOPE_REQUEST);
        String obj = (String)ra.getAttribute("auth_url",RequestAttributes.SCOPE_REQUEST);
        String act = (String)ra.getAttribute("auth_act",RequestAttributes.SCOPE_REQUEST);
        Assert.hasText(sub,"sub not null");
        Assert.hasText(obj,"obj not null");
        Assert.hasText(act,"act not null");

        RegisteredQuerySpec registeredQuerySpec = (RegisteredQuerySpec)context.getBean("registeredQuerySpec");
        Enforcer enforcer = (Enforcer)context.getBean("enforcer");
        Specification<T> spec = registeredQuerySpec.get(super.getDomainClass().getSimpleName())
                                 .getSpec(sub,obj,act);
        return super.findAll(spec);
    }

    private   Specification<T> getAll(String sub, String obj, String act){
              return (root,query,builder) -> builder.equal(root.get("getCreateSimpleName").as(String.class),sub);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return super.findAllById(ids);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return super.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return super.findAll(spec);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return super.findAll(spec, sort);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return super.findOne(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return super.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return super.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return super.findAll(example, pageable);
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return super.findBy(example, queryFunction);
    }

    @Override
    public <S extends T, R> R findBy(Specification<T> spec, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return super.findBy(spec, queryFunction);
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public long count(Specification<T> spec) {
        return super.count(spec);
    }

    @Override
    public void flush() {
        super.flush();
    }

    @Override
    public <S extends T> S save(S entity) {

        return super.save(entity);
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return super.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return super.saveAll(entities);
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return super.saveAllAndFlush(entities);
    }
}
