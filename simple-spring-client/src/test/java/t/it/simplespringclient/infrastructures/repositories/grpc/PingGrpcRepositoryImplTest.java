package t.it.simplespringclient.infrastructures.repositories.grpc;

import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.models.requests.Ping;
import t.it.simplespringclient.models.responses.Pong;
import t.it.simplespringclient.stubs.PingServiceStubGrpc;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(value = {MockitoExtension.class})
class PingGrpcRepositoryImplTest {
    @Mock
    private PingServiceStubGrpc.PingServiceStubStub pingStub;
    private PingRepository pingRepository;

    @BeforeEach
    void setUp() {
        pingRepository = new PingGrpcRepositoryImpl(pingStub);
    }

    @Test
    void testInit() {
        assertNotNull(pingRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testPing() {
        doAnswer(invocation -> {
            StreamObserver<Pong> pongStreamObserver = ((StreamObserver<Pong>) invocation.getArguments()[1]);
            pongStreamObserver.onNext(Pong.newBuilder().setMessage("pong").build());
            pongStreamObserver.onCompleted();
            return invocation;
        }).when(pingStub).request(any(Ping.class), any(StreamObserver.class));

        Flux<String> ping = pingRepository.ping();
        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();

        verify(pingStub, times(1)).request(any(Ping.class), any(StreamObserver.class));
    }
}