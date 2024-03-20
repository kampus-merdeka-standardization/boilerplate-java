package t.it.simplegraphql.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t.it.simplegraphql.dto.PongResponse;
import t.it.simplegraphql.repositories.SimpleRepository;

@Service
@RequiredArgsConstructor
public class SimpleService {
    private final SimpleRepository simpleRepository;

    public PongResponse ping() {
        return new PongResponse(simpleRepository.getPong());
    }
}
