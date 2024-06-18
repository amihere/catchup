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

      String message = "Here we are now";
      channel.basicPublish(EXCHANGE_NAME, "123456789", null,
                           message.getBytes("UTF-8"));

      System.out.println(" [x] Sent ");
    }
  }
}
