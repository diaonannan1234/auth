package org.example.task.policy;

import org.apache.commons.io.IOUtils;
import org.casbin.jcasbin.exception.CasbinAdapterException;
import org.casbin.jcasbin.model.Model;
import org.casbin.jcasbin.persist.Adapter;
import org.casbin.jcasbin.persist.Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public  class PolicyAdapter extends AbstractAdapter {

    private PolicyRepo policyRepo;

    public PolicyAdapter(){
        PolicyRepo policyRepo = new PolicyRepo();
        List<Policy> p = new ArrayList<>();
        p.add( new Policy("alice","/task", Act.GET));
        p.add( new Policy("bob","/task", Act.POST));
        p.add( new Policy("lisi","/task", Act.DELETE));
        p.add( new Policy("bob","/task", Act.PUT));
        policyRepo.addPolicyList(p);
        this.policyRepo = policyRepo;
    }
    @Override
    public void loadPolicy(Model model) {
        loadPolicyData(model, Helper::loadPolicyLine, policyRepo.getPolicyList());
    }

    @Override
    public void savePolicy(Model model) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void addPolicy(String sec, String ptype, List<String> rule) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void removePolicy(String sec, String ptype, List<String> rule) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void removeFilteredPolicy(String sec, String ptype, int fieldIndex, String... fieldValues) {
        throw new UnsupportedOperationException("not implemented");
    }

    private void loadPolicyData(Model model, Helper.loadPolicyLineHandler<String, Model> handler,List<Policy> policyList) {
            policyList.forEach(x -> handler.accept(x.getPolicy(), model));

    }

    @Override
    protected String getAdapterName() {
        return "rbac";
    }
}
