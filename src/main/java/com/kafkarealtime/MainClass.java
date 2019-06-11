package com.kafkarealtime;

public class MainClass {

    public static void main(String[] args) throws Exception{
        LogListener logListener=new LogListener();
        logListener.runReadFolder();

    }
}
