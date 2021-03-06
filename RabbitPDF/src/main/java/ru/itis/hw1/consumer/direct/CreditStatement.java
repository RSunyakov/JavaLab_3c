package ru.itis.hw1.consumer.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.hw1.models.User;
import ru.itis.hw1.utils.PdfGenerator;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreditStatement {
    private final static String CREDIT_QUEUE_NAME = "credit_queue";
    private final static String TEMPLATE_NAME = "bank/credit";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.basicConsume(CREDIT_QUEUE_NAME, false, (consumerTag, message) -> {
                try {
                    PdfGenerator.generatePdf(User.deserialize(message.getBody()), TEMPLATE_NAME);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
