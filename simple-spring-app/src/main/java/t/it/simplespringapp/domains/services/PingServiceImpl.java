package t.it.simplespringapp.domains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.it.simplespringapp.domains.repositories.PingRepository;
import t.it.simplespringapp.applications.models.responses.PongResponse;

@Service
@RequiredArgsConstructor
public class PingServiceImpl implements PingService {
    private final PingRepository pingRepository;

    @Override
    public String ping() {
        return pingRepository.getPong();
    }
}
