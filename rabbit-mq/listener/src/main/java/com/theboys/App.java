package com.theboys;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class App {
  private static final String EXCHANGE_NAME = "the.boys";

  public static void main(String[] args) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");

    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {

      String queueName = "q1";
      boolean autoAck = false;
            channel.basicConsume(queueName, autoAck, "myConsumerTag",
            new DefaultConsumer(channel) {
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope,
                                   AMQP.BasicProperties properties, byte[] body)
            throws IOException {
          String routingKey = envelope.getRoutingKey();
          String contentType = properties.getContentType();
          long deliveryTag = envelope.getDeliveryTag();
        }
            }
    }
