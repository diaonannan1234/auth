package org.example.task.policy;

import lombok.Data;
import org.springframework.util.Assert;


public class Policy {

    private static final String p = "p";

    private String sub;

    private String url;

    private Act act;

    public Policy(String sub,String url){
        this(sub,url,null);
    }

    public Policy(String sub,String url,Act act){
        Assert.hasText(sub,"Policy_sub not value");
        Assert.hasText(url,"Policy_url not value");

        this.sub = sub;
        this.url = url;
        this.act = act != null ? act : Act.GET;
    }

    public String getPolicy(){
        return this.p + ","+this.sub + "," + this.url +  "," +  this.act;
    }
}
