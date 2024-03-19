package t.it.simplerest.webmvc.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class SimpleRepository {
    public String getPong() {
        return "pong";
    }
}
