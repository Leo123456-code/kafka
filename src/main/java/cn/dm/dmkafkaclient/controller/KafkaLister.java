package cn.dm.dmkafkaclient.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * ClassName: KafkaLister
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/13-16:44
 * email 1437665365@qq.com
 */
@Component
@Slf4j
public class KafkaLister {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @KafkaListener(topics = "${kafka.topic.order}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload String msg){
        String json = gson.toJson(msg);
        log.info("miniooc receive message={}",gson.toJson(json));
    }
}
