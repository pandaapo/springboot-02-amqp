package com.panda.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、有自动配置了连接工厂ConnectionFactory
 * 3、RabbitPropertiest封装了RabbitMQ的配置
 * 4、RabbitTemplate：给RabbitMQ发动和接受消息
 * 5、AmqpAdmin：RabbitMQ系统管理功能组件：不来收发消息，用来创建队列、交换器等等。
 *      5-1、创建queue，exchange, binding
 * 6、@EnableRabbit + @RabbitListener :监听消息队列中的内容
 */
//开启基于注解的RabbitMQ模式
@EnableRabbit
@SpringBootApplication
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }
}
