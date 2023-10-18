package org.example.task.core.casbin;

import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.Adapter;

public class ModelAndAdapter {

    private Model model;

    private Adapter adapter;

    public ModelAndAdapter(Model model,Adapter adapter){
        this.adapter = adapter;
        this.model = model;
    }
}
