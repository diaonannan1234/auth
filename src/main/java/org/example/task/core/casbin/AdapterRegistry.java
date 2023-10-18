package org.example.task.core.casbin;

import org.casbin.jcasbin.persist.Adapter;
import org.example.task.policy.AbstractAdapter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

public class AdapterRegistry implements ApplicationContextAware {

    private Map<String, Adapter> m = new HashMap<>(60);

    private ApplicationContext context;
    public AdapterRegistry(){
        init();
    }

    private void init(){
       String[] name = context.getBeanNamesForAnnotation(org.example.task.core.casbin.Adapter.class);
        for (String n : name){
            AbstractAdapter adapter = (AbstractAdapter)context.getBean(n);
            m.put(adapter.getAdapterName(),adapter);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
