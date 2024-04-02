package t.it.simplespringapp.applications.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.it.simplespringapp.domains.repositories.PingRepository;
import t.it.simplespringapp.interfaces.models.responses.PongResponse;

@Service
@RequiredArgsConstructor
public class PingServiceImpl implements PingService {
    private final PingRepository pingRepository;

    @Override
    public PongResponse ping() {
        return PongResponse.builder()
                .message(pingRepository.getPong())
                .build();
    }
}
