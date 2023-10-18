package org.example.task.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PolicyRepo {

    private List<Policy> policyList;

    public PolicyRepo(){
        this.policyList = new ArrayList<>(30);
    }

    public void addPolicyList(Collection policyList){
        if (!policyList.isEmpty()) this.policyList.addAll(policyList);
    }

    public void addPolicy(Policy policy){
        if (policy != null )this.policyList.add(policy);
    }

    public List<Policy> getPolicyList(){
        return this.policyList;
    }
}
