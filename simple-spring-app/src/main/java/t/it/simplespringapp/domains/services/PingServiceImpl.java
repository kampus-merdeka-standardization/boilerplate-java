package t.it.simplespringapp.domains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PingServiceImpl implements PingService {

    @Override
    public Mono<String> ping() {
        return Mono.just("pong");
    }
}
