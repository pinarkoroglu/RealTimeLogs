package com.kafkarealtime;
import com.kafkarealtime.Interfaces.IKafkaConstants;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerCreator {

    private Producer producer=createProducer();
    /*kafka producer creator */
    public static Producer<String, String> createProducer() {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<String,String>(properties);
    }

    public void runProducer(String message) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                    IKafkaConstants.TOPIC_NAME, message);
        try {
            producer.send(record);
            System.out.println(record.value());
        }catch (Exception exception){
            System.out.println(exception);
        }
             //producer.close();
    }


}