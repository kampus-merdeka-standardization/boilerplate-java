package t.it.simplespringmonitoring.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import t.it.simplespringmonitoring.domain.repositories.GreetingRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class GreetingServiceImpl implements GreetingService {
    private final GreetingRepository greetingRepository;

    @Override
    public Mono<String> doGreeting(String name) {
        log.info("greeting {}", name);
        return greetingRepository.getGreeting(name);
    }
}
