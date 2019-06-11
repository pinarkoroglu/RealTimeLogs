package com.kafkarealtime;
import java.io.*;


public class LogListener {
    KafkaProducerCreator kafkaProducerCreator;
    public static String directoryName="logserver";
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
public static void runReadFolder() throws Exception{

        while(true){
            readFolder();
        }
}

public static void readFolder() throws Exception
    {
        File files=getLogServerDirectory();
        if(!files.exists()){
            System.out.println("There is no Folder!");
            Thread.sleep(3000);
            readFolder();
    }
            System.out.println(files.getName());
                readFile(files);

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
    public static File getLogServerDirectory() throws Exception{
        File directory = new File(System.getProperty("user.dir")+"/"+directoryName);
        File[] files = directory.listFiles(logFilefilter);
        return files != null ? files[0] : null;
    }

}






