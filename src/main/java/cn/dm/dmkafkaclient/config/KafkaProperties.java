package cn.dm.dmkafkaclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: KafkaProperties
 * Description: TODO
 * Author: Leo
 * Date: 2020/5/11-20:16
 * email 1437665365@qq.com
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootstrapServers;
    private String acksConfig;
}
