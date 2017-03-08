package pl.sda.mongo;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        PostsDAO dao = new PostsDAO();

        Post post = new Post();
        post.setAuthor("Lukasz");
        post.setContent("Tresc z parametru");
        post.setDate(new Date());
        dao.insert(post);

        List<DBObject> result = dao.searchById(new ObjectId("58c03d1fb5fb1222a4cc3353"));

        Post post2 = new Post();
        post2.setAuthor("Zdzisiu");
        post2.setContent("Zaktualizowano tersc z parametru");
        post2.setDate(new Date());

        dao.update(post2, new ObjectId("58c051ee0f71cc163cbb6891"));

        dao.removeById(new ObjectId("58c03c67b5fb1222a4cc3331"));
        System.out.println("");
    }
}
