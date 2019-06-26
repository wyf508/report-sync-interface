package com.djhu.listener;

import com.djhu.config.RabbitConfig;
import com.djhu.service.ReportSyncService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yf.wu
 * @date 2019-06-24-10:44
 */
@Component
public class ReportSyncListener {

    private final Logger logger = LoggerFactory.getLogger(ReportSyncListener.class);

    @Autowired
    private ReportSyncService reportSyncService;

    @RabbitListener(queues = RabbitConfig.QUEUE, containerFactory = "singleListenerContainer")
    public void process(Message message, Channel channel) {
        try {
            String dataMsg = new String(message.getBody(), "utf-8");
            try {
                logger.info("rabbitMq receive msg : " + dataMsg);
                reportSyncService.handleReceiveData(dataMsg);
            } catch (Exception e) {
                logger.error("Receive数据处理出现异常：" + e.getMessage(), e);
                throw e;
            }
            //确认消息成功消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
