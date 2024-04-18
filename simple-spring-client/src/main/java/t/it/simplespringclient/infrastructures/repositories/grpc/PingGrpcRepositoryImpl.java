package t.it.simplespringclient.infrastructures.repositories.grpc;


import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.models.responses.WebResponse;
import t.it.simplespringclient.services.PingServiceGrpc;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PingGrpcRepositoryImpl implements PingRepository {
    private final PingServiceGrpc.PingServiceStub stub;

    @Override
    public Flux<String> ping() {
        Sinks.Many<String> pong = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
        StreamObserver<WebResponse> pongResponse = new StreamObserver<>() {

            @Override
            public void onNext(WebResponse value) {
                pong.tryEmitNext(value.getData());
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
        stub.ping(Empty.newBuilder().build(), pongResponse);
        return pong.asFlux();
    }
}
