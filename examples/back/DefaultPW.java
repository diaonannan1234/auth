package org.example;

public class DefaultPW implements Password{
    private String pw;

    private Employee employee;

    public DefaultPW(String pw,Employee employee){
        this.employee = employee;
        this.pw = pw;
    }

    private  Boolean checkLen(){
        return pw.length() > 8;
    }

    private Boolean  checkComplexity(){
        return false;
    }



    @Override
    public Boolean check() {

        return checkLen() && checkComplexity() ;
    }
}
