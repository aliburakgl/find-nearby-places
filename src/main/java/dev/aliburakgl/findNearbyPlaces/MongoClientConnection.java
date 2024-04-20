package dev.aliburakgl.findNearbyPlaces;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoClientConnection {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://aliburakgl:w0Empo0sQMRsjSiU@findnearbyplaces.cvhpt7e.mongodb.net/?retryWrites=true&w=majority&appName=FindNearbyPlaces";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase("find-nearby-places-db");
                MongoCollection<Document> places = database.getCollection("places");
                String json = PlacesAPI.main();
                Document document = Document.parse(json);
                places.insertOne(document);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
