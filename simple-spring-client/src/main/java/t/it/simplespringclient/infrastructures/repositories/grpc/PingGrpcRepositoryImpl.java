package t.it.simplespringclient.infrastructures.repositories.grpc;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.models.requests.Ping;
import t.it.simplespringclient.models.responses.Pong;
import t.it.simplespringclient.stubs.PingServiceStubGrpc;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PingGrpcRepositoryImpl implements PingRepository {
    private final PingServiceStubGrpc.PingServiceStubStub stub;

    @Override
    public Flux<String> ping() {
        Ping ping = Ping.newBuilder().setMessage("ping").build();
        Sinks.Many<String> pong = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
        StreamObserver<Pong> pongResponse = new StreamObserver<>() {

            @Override
            public void onNext(Pong value) {
                pong.tryEmitNext(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                log.error("Error: ", t);
                pong.tryEmitError(t);
            }

            @Override
            public void onCompleted() {
                log.info("completed");
                pong.tryEmitComplete();
            }
        };
        stub.request(ping, pongResponse);
        return pong.asFlux();
    }
}
