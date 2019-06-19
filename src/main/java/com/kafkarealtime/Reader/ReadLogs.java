package com.kafkarealtime.Reader;

import com.kafkarealtime.Producer.KafkaProducerCreator;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.File;
import java.io.FileFilter;


public class LogListener extends TailerListenerAdapter {

    public static String directoryName="logserver";
    private static File file;
    private TailerListener listener;
    private static Thread thread;
    private static Tailer tailer;
    private static KafkaProducerCreator producer;

    public LogListener(KafkaProducerCreator producer) {
        this.producer = producer;
        this.listener= new MyTailerListener(this.producer);
    }


    public void runReadFolder(){
        checkFile();
        Tailer();
    }

    public static File getLogServerDirectory()  {
        File directory = new File(System.getProperty("user.dir")+"/"+directoryName);
        File[] files = directory.listFiles(logFilefilter);
        return files != null ? files[0] : null;
    }
    private void checkFile() {
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    if(file != null){
                        if(!file.exists()){
                            System.out.println("There is no Folder!");
                            tailer.stop();
                            Tailer();
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
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
    private void Tailer() {
        file = getLogServerDirectory();
        tailer = new Tailer(file, this.listener, 1000);
        thread = new Thread(tailer);
        thread.start();
    }





}





