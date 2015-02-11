//package fr.unice.polytech.directace.processor;
//
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.QueueingConsumer;
//
//import java.io.IOException;
//
///**
// * InputDataAccess class
// * Interface to retrieve sensor values from the message queue
// */
//public class InputDataAccess {
//
//	private final static String DESTINATION_URL = "54.154.176.223";
//    private final static int DESTINATION_PORT = 15672;
//    private final static String USER_NAME_PASSWORD = "guest";
//	private final static String QUEUE_NAME      = "sensor-values-queue";
//
//
//
//	/**
//	 * Create a message consumer that call the given message listener each time a sensor message is received
//	 */
//	public static void createMessageListener (MessageListener messageListener) throws JMSException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername(USER_NAME_PASSWORD);
//        factory.setPassword(USER_NAME_PASSWORD);
//        factory.setHost(DESTINATION_URL);
//        factory.setPort(DESTINATION_PORT);
//        Connection conn;
//        try {
//            conn = factory.newConnection();
//            Channel channel = conn.createChannel();
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//            QueueingConsumer consumer = new QueueingConsumer(channel);
//            channel.basicConsume(QUEUE_NAME, false, consumer);
//            while (true) { // you might want to implement some loop-finishing
//                // logic here ;)
//                QueueingConsumer.Delivery delivery;
//                try {
//                    delivery = consumer.nextDelivery();
//                    String message = new String(delivery.getBody());
//                    System.out.println("Received message: " + message);
//
//                    System.out.println("Done !");
//                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//                } catch (InterruptedException ie) {
//                    continue;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
//}
