package cn.dm.dmkafkaclient.config;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ClassName: KafkaConfig
 * Description: TODO
 * Author: Leo   192.168.1.128
 * Date: 2020/5/11-20:14
 * email 1437665365@qq.com
 */
@Configuration
public class KafkaConfig {
    //创建topic 名称
    public final static String TOPIC_NAME = "springboot_topic";

    @Autowired
    private KafkaProperties kafkaProperties;


    //程序启动时创建Topic
    @Bean
    public AdminClient adminClient(){
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServers());
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }

//    @Bean
//    public CreateTopicsResult newTopic(){
//        AdminClient adminClient = adminClient();
//        //创建topic
//        //副本因子
//        Short rs = 1;
//        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, rs);
//        //topic是一个集合
//        CreateTopicsResult topics = adminClient.createTopics(Arrays.asList(newTopic));
//        return topics;
//
//    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return new DefaultKafkaProducerFactory<>(props);
    }







    @Bean
    public KafkaProducer kafkaProducer(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServers());

        properties.put(ProducerConfig.ACKS_CONFIG,kafkaProperties.getAcksConfig());
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //producer 主对象
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        return producer;
    }
     //在KafkasConsumerConfig中已经整合
//    @Bean
//    public KafkaConsumer kafkaConsumer(){
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServers());
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
//        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"15000");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
//        //consumer 主对象
//        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
//        return consumer;
//    }



}
