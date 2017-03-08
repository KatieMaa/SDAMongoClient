package pl.sda.mongo;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class PostsDAO {
    public WriteResult insert(Post post) {
        DBCollection posts = getDbCollection();

        BasicDBObject newPost = new BasicDBObject();
        newPost.put("author", post.getAuthor());
        newPost.put("content", post.getContent());
        newPost.put("date", post.getDate());

        return posts.insert(newPost);
    }

    public List<DBObject> searchById(ObjectId id) {
        DBCollection posts = getDbCollection();

        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        DBCursor postCursor = posts.find(query);

        List<DBObject> result = new ArrayList<>();
        while (postCursor.hasNext()) {
            DBObject post = postCursor.next();
            result.add(post);
        }
        return result;
    }

    public void removeById(ObjectId id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        DBCollection posts = getDbCollection();
        posts.findAndRemove(query);
    }

    public WriteResult update(Post post, ObjectId id) {
        DBCollection posts = getDbCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);

        BasicDBObject updatedPost = new BasicDBObject();
        updatedPost.put("author", post.getAuthor());
        updatedPost.put("content", post.getContent());
        updatedPost.put("date", post.getDate());

        return posts.update(query, updatedPost);
    }

    private DBCollection getDbCollection() {
        Mongo mongoClient = new Mongo("localhost", 27017);
        return mongoClient.getDB("SDATest").getCollection("posts");
    }
}
