package com.linoer.demo.design_pattern.facade;

public class Client {
    private static SecurityFacade securityFacade;
    public static void Main(String[] args){
        securityFacade = new SecurityFacade();
        securityFacade.active();
    }
}
