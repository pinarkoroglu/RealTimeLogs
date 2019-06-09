package com.kafkarealtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogListener {
    /* variables */
    static public List<String> istanbul = new ArrayList<>();
    static public  List<String> tokyo = new ArrayList<>();
    static List<String> beijing = new ArrayList<>();
    static List<String> moskow = new ArrayList<>();
    static List<String> london = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readFile();
    }

    public static void readFile() throws Exception{
        String filePath="/home/pinar/logserver/log_1";
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String log;
        while (true) {
            log = br.readLine();

            if (log == null) {
                Thread.sleep(1000);
                log = br.readLine();
            }
            String[] logs = log.split("\\r?\\n");

            for (String line : logs) {
                String []lines=line.split(" ");

                if(lines[3].equals("Istanbul"))
                    istanbul.add(line);
                else if(lines[3].equals("Tokyo"))
                    tokyo.add(line);
                else if(lines[3].equals("Beijing"))
                    beijing.add(line);
                else if(lines[3].equals("London"))
                    london.add(line);
                else if(lines[3].equals("Moskow"))
                    moskow.add(line);
            }
           // System.out.println(log);
        }

    }
}
