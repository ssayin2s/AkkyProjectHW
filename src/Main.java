public class Main {
    public static void main(String[] args) {
        // Create an instance of ProducerApp
        KafkaProducerApp producerApp = new KafkaProducerApp();

        // Create an instance of ConsumerApp
        KafkaConsumerApp consumerApp = new KafkaConsumerApp();

        // Start the producer application
        producerApp.start();

        // Start the consumer application
        consumerApp.start();
    }
}
