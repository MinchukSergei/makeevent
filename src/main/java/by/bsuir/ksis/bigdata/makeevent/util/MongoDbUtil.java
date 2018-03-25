package by.bsuir.ksis.bigdata.makeevent.util;

import com.mongodb.client.MongoCollection;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbUtil {

    public static MongoCollection getCollectionByClass(MongoTemplate mongoTemplate, Class dtoClass) {
        String collectionName = mongoTemplate.getCollectionName(dtoClass);
        return mongoTemplate.getCollection(collectionName);
    }
}
