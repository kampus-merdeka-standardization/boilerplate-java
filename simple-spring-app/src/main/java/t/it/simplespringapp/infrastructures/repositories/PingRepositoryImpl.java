package t.it.simplespringapp.infrastructures.repositories;

import org.springframework.stereotype.Repository;
import t.it.simplespringapp.domains.repositories.PingRepository;

@Repository
public class PingRepositoryImpl implements PingRepository {
    @Override
    public String getPong() {
        return "pong";
    }
}
