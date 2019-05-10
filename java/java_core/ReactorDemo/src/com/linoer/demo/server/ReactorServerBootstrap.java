package com.linoer.demo.server;

import java.io.IOException;

public class ReactorServerBootstrap {
    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(2333)).start();
    }
}
