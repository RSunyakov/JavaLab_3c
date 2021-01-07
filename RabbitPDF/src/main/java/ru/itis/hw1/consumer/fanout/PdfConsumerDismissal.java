package ru.itis.hw1.consumer.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.hw1.models.User;
import ru.itis.hw1.utils.PdfGenerator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class PdfConsumerDismissal {

    private final static String EXCHANGE_NAME = "pdf";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String TEMPLATE_NAME = "dismissal";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            // создаем временную очередь со случайным названием
            String queue = channel.queueDeclare().getQueue();

            // привязали очередь к EXCHANGE_NAME
            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    User user = User.deserialize(message.getBody());
                    PdfGenerator.generatePdf(user, TEMPLATE_NAME);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (ClassNotFoundException e) {
                    System.err.println("Deserialization failed");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
