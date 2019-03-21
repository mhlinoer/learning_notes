package com.linoer.demo.design_pattern.facade;

public class SecurityFacade {
    private static Camera camera1;
    private static Camera camera2;
    private static Light light1;
    private static Light light2;


    public SecurityFacade(){
        camera1 = new Camera();
        camera2 = new Camera();
        light1 = new Light();
        light2 = new Light();
    }

    public void active(){
        camera1.turnOn();
        camera2.turnOn();
        light1.turnOn();
        light2.turnOn();
    }

}
