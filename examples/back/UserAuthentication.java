package org.example;

import org.springframework.util.Assert;

import java.net.PasswordAuthentication;

public class UserAuthentication implements Authentication{

    private Password password;

    private Employee employee;

    private UserAuthentication(String password,Employee employee){
        Assert.hasText(password,"password is null");
        Assert.notNull(employee,"用户不存在");
        this.password = new DefaultPW(password,employee);
        this.employee = employee;
    }

    public static final Authentication create(String password,Employee employee){
        return new UserAuthentication(password,employee);
    }

    public Boolean auth() throws AuthenticationException {
        // 如果密码长度、复杂度符合要求
       return password.check();
    }
}