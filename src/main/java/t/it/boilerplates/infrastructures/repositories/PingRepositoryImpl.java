package t.it.boilerplates.infrastructures.repositories;

import org.springframework.stereotype.Repository;
import t.it.boilerplates.domains.repositories.PingRepository;

@Repository
public class PingRepositoryImpl implements PingRepository {
    @Override
    public String getPong() {
        return "pong";
    }
}
