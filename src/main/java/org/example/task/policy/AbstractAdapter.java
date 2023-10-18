package org.example.task.policy;

import org.casbin.jcasbin.persist.Adapter;

public abstract class AbstractAdapter implements Adapter {


    public abstract String getAdapterName();

}
