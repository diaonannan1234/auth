package org.example.task.core;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class RegisteredQuerySpec {
    public  ConcurrentMap<String, IQuerySpec> map;

    public RegisteredQuerySpec(){
        map = new ConcurrentHashMap<>(60);
    }

    public  void register(IQuerySpec spec){
        map.put(spec.getTypeName(),spec);
    }

    public IQuerySpec get(String typeName){
        return map.get(typeName);
    }

}
