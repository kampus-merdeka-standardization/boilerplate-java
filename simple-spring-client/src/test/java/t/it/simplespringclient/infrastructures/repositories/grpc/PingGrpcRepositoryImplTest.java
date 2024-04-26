package t.it.simplespringclient.infrastructures.repositories.grpc;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import t.it.simplespringclient.domains.repositories.PingRepository;
import t.it.simplespringclient.models.responses.MetaResponse;
import t.it.simplespringclient.models.responses.WebResponse;
import t.it.simplespringclient.services.PingServiceGrpc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@ExtendWith(value = {MockitoExtension.class})
class PingGrpcRepositoryImplTest {
    @Mock
    private PingServiceGrpc.PingServiceStub pingStub;
    private PingRepository pingRepository;

    @BeforeEach
    void setUp() {
        pingRepository = new PingGrpcRepositoryImpl(pingStub);
    }

    @Test
    void testInit() {
        assertNotNull(pingRepository);
    }

    @Test
    void testPing_Success() {
        doAnswer(invocation -> {
            StreamObserver<WebResponse> pongStreamObserver = ((StreamObserver<WebResponse>) invocation.getArguments()[1]);
            pongStreamObserver.onNext(WebResponse.newBuilder().setMeta(MetaResponse.newBuilder().setCode(String.valueOf(Status.OK.getCode().value())).setMessage(Status.OK.getCode().toString()).build()).setData("pong").build());
            pongStreamObserver.onCompleted();
            return pongStreamObserver;
        }).when(pingStub).ping(any(Empty.class), any(StreamObserver.class));

        Flux<String> ping = pingRepository.ping();
        StepVerifier.create(ping)
                .expectNext("pong")
                .verifyComplete();

        verify(pingStub, times(1)).ping(any(Empty.class), any(StreamObserver.class));
    }

    @Test
    void testPing_Fail() {
        doAnswer(invocation -> {
            StreamObserver<WebResponse> pongStreamObserver = ((StreamObserver<WebResponse>) invocation.getArguments()[1]);
            pongStreamObserver.onError(Status.INTERNAL.withDescription("Internal error").asRuntimeException());
            pongStreamObserver.onCompleted();
            return pongStreamObserver;
        }).when(pingStub).ping(any(Empty.class), any(StreamObserver.class));

        Flux<String> ping = pingRepository.ping();
        StepVerifier.create(ping)
                .expectError()
                .verify();

        verify(pingStub, times(1)).ping(any(Empty.class), any(StreamObserver.class));
    }
}