package com.agh.cs.ds.jgroups;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        new DistributedStringMap().start();
    }
}
