package com.kafkarealtime;
import java.io.*;


public class LogListener {
    KafkaProducerCreator kafkaProducerCreator;
    public static void readFile(File file) throws  Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String log;

        while (true) {
            log = br.readLine();

            if (log == null) {

                Thread.sleep(2000);
                log = br.readLine();
                break;
            }
/*
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

 */
            KafkaProducerCreator.runProducer(log);
            System.out.println(log);
        }


    }
public static void runReadFolder(String folderPath) throws Exception{
        while(true){
            readFolder(folderPath);
        }
}

public static void readFolder(String folderPath) throws Exception
    {
        File directory = new File(folderPath);
        File[] files = directory.listFiles(logFilefilter);
        //Let's list out the filtered files
        if(files.length==0){
            System.out.println("There is no Folder!");
            Thread.sleep(3000);
            readFolder(folderPath);
    }
            for (File f : files)
            {
                System.out.println(f.getName());
                readFile(f);
            }
    }

    static FileFilter logFilefilter = new FileFilter() {
        //Override accept method
        public boolean accept(File file) {
            //if the file extension is .log return true, else false
            if (file.getName().startsWith("log_")) {
                return true;
            }
            return false;
        }
    };
}






