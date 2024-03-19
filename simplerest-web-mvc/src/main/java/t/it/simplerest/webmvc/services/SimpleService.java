package t.it.simplerest.webmvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.it.simplerest.webmvc.dto.PongResponse;
import t.it.simplerest.webmvc.repositories.SimpleRepository;

@Service
@RequiredArgsConstructor
public class SimpleService {
    private final SimpleRepository simpleRepository;

    public PongResponse ping() {
        return new PongResponse(simpleRepository.getPong());
    }
}
