package org.example.task.core;

import jakarta.annotation.Nullable;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.casbin.jcasbin.main.Enforcer;
import org.example.task.TaskEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseEntity<Id extends Serializable> extends AbstractPersistable<Id> implements CreateEmployee<Id> { // <2>

    private Id createID;

    private String createName;

    private String simpleName;

    public final void addCreateEmployee(Id createID,String createName,String simpleName){
        this.createID = createID;
        this.createName = createName;
        this.simpleName = simpleName;
    }


    @Version
    private Long version;

    public  Optional<Long> getVersion(){
        return Optional.ofNullable(version);
    }

    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }

    @Override
    public Id getCreateID() {
        return this.createID;
    }

    @Override
    public String getCreateName() {
        return this.createName;
    }

    @Override
    public String getCreateSimpleName() {
        return this.simpleName;
    }


}

