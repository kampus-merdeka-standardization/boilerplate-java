package t.it.simplegraphql.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class SimpleRepository {
    public String getPong() {
        return "pong";
    }
}
