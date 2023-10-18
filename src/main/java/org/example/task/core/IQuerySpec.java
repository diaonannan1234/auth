package org.example.task.core;

import org.example.task.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

public interface IQuerySpec {
   <T> Specification<T> getSpec(String sub, String obj, String act);

    String getTypeName();

}
