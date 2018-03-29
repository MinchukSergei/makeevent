package by.bsuir.ksis.bigdata.makeevent.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMongoDbConfiguration {
    private final MongoClient mongoClient;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Autowired
    public SpringMongoDbConfiguration(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Bean
    public Morphia getMorphia() {
        return new Morphia();
    }

    @Bean
    public Datastore getDatastore() {
        return getMorphia().createDatastore(mongoClient, databaseName);
    }
}
