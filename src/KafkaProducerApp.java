import org.apache.kafka.clients.producer.*;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.*;

public class KafkaProducerApp {

    private static final String TOPIC = "my_topic";


    public static void main(String[] args) throws InterruptedException {
        // Set up Kafka producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // Create Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Connect to MongoDB and retrieve the collection
        MongoClient mongoClient = new MongoClient("localhost", 27017);        MongoDatabase database = mongoClient.getDatabase("ogrenciler");
        MongoCollection<Document> collection = database.getCollection("kimlik");

        // Keep track of the last processed document's _id
        String lastProcessedId = "";

        while (true) {
            // Query MongoDB for new documents
            Document query = new Document("_id", new Document("$gt", lastProcessedId));
            MongoCursor<Document> cursor = collection.find(query).iterator();

            while (cursor.hasNext()) {
                Document document = cursor.next();

                // Convert MongoDB document to JSON string
                JsonWriterSettings settings = JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).build();
                String json = document.toJson(settings);

                // Publish the JSON message to Kafka
                producer.send(new ProducerRecord<>(TOPIC, json), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            System.err.println("Error publishing message: " + exception.getMessage());
                        }
                    }
                });

                // Update the last processed _id
                lastProcessedId = document.getObjectId("_id").toString();
            }

            // Pause for 10 seconds
            Thread.sleep(10000);
        }
    }

    public void start() {
    }
}
