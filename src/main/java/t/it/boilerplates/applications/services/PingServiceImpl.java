package t.it.boilerplates.applications.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.it.boilerplates.interfaces.models.responses.PongResponse;
import t.it.boilerplates.domains.repositories.PingRepository;

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
