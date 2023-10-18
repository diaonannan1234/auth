package org.example.task;

import org.casbin.jcasbin.main.Enforcer;
import org.example.task.core.IQuerySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class QuerySpec  implements IQuerySpec {

    @Autowired
    private Enforcer enforcer;

    @Override
    public Specification<TaskEntity> getSpec(String sub,String obj,String act){

        if (enforcer.enforce(sub, obj, act)) {
            return (root,query,builder) -> builder.ge(root.get("number").as(Integer.class),10);
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.le(root.get("number").as(Integer.class),10));
        }

    }

    @Override
    public String getTypeName() {
        return TaskEntity.class.getSimpleName();
    }
}
