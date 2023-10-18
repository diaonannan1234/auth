package org.example;


import com.github.javafaker.Stock;

public class TokenAuthentication implements Authentication{

    private String token;

    private String ipAddress;

    private String sourceMode;

    //private TokenServer tokenServer;

    public TokenAuthentication(String token, String ipAddress,String sourceMode){
        this.ipAddress = ipAddress;
        this.token = token;
        this.sourceMode = sourceMode;
    }

    @Override
    public Boolean auth() throws AuthenticationException {

        //相同IP的Token要相同,判断Token是否过期,  从Token
        //tokenServer.auth(this.token,this.sourceMode,this.ipAddress);

        return null;
    }
}
