package com.panda.amqp;

import com.panda.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange_queue_binding(){
//        amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
//        System.out.println("创建Exchange完成");

//        amqpAdmin.declareQueue(new Queue("amqp.queue", true));
//        System.out.println("创建Queue完成");

        amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,"amqp.exchange","amqp.hello",null));
        System.out.println("绑定完成");
    }

    /**
     * 1、单播
     */
    @Test
    public void contextLoads() {
        //自己构造Message；定义消息体内容和消息头
        //rabbitTemplate.send(exchange, routingKey, message);

        //object默认当成消息体，传入对象后，自动序列化
        //rabbitTemplate.convertAndSend(exchange, routingKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news", new Book("《人月神话》","小弗雷德里克.布鲁克斯"));
    }

    /**
     * 广播
     */
    @Test
    public void sendFanout(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("hello","你好"));
    }

    //接受数据
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }


}
