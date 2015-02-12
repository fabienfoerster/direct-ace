package fr.unice.polytech.directace.processor;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;


/**
 * Message processor application
 * Consumes messages from the message queue, process data and store raw sensor data into the database
 */
public class App {

    private final static String DESTINATION_URL = "54.154.176.223";
    private final static int DESTINATION_PORT = 15672;
    private final static String QUEUE_NAME = "sensor-values-queue";
    private final static String USER_NAME_PASSWORD = "guest";

    private OutputDataAccess outputDataAccess;
    private int count;


    public App() throws Exception {
        // Create output proxy
        outputDataAccess = new OutputDataAccess();
        count = 0;
    }

    public void start() {
        System.out.println("Begin of start method");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USER_NAME_PASSWORD);
        factory.setPassword(USER_NAME_PASSWORD);
        factory.setHost(DESTINATION_URL);
        factory.setPort(DESTINATION_PORT);
        Connection conn;
        try {
            conn = factory.newConnection();
            Channel channel = conn.createChannel();
            //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, false, consumer);
            while (true) {
                QueueingConsumer.Delivery delivery;
                try {
                    delivery = consumer.nextDelivery();
                    String message = new String(delivery.getBody());
                    System.out.println("Received message: " + message);
                    processMessage(message);
                    System.out.println("Done !");
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException ie) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {
        // Extract sensor information
        JSONObject jsonObject = new JSONObject(new JSONTokener(message));
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("event");
        String date = jsonObject.getString("date");
        String value = jsonObject.getString("value");
        String playerID = jsonObject.getString("playerID");
        String matchID = jsonObject.getString("matchID");

        // TODO: Make the value pass through multiples processors to process it

        // Save sensor data into the database

        boolean res = outputDataAccess.saveMatchLog(id, name, date, value, playerID, matchID);
        if (res) {
            System.out.println("Add in base");
        }
    }


    public static void main(String[] args) throws Exception {
        new App().start();
    }
}
