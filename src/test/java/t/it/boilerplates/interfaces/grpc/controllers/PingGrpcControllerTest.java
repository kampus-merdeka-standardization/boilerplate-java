package t.it.boilerplates.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import t.it.boilerplates.PingRequest;
import t.it.boilerplates.PongResponse;
import t.it.boilerplates.applications.services.PingService;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@Slf4j
@SpringJUnitConfig(PingGrpcController.class)
class PingGrpcControllerTest {
    @Autowired
    private PingGrpcController pingGrpcController;

    @MockBean
    private PingService pingService;

    @Test
    void testPing() {
        Mockito.when(pingService.ping()).thenReturn(PongResponse.newBuilder().setMessage("pong").build());

        PingRequest ping = PingRequest.newBuilder().setRequest("ping").build();
        StreamObserver<PongResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(PongResponse value) {
                log.info("got response: " + value.getMessage());
                assertNotNull(value);
                assertEquals(PongResponse.newBuilder()
                        .setMessage("pong")
                        .build(), value);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
        pingGrpcController.ping(ping, responseObserver);
    }

    @Test
    void testPing_ShouldThrowException() {
        Mockito.when(pingService.ping()).thenThrow(new RuntimeException("Fail to get response"));

        PingRequest ping = PingRequest.newBuilder().setRequest("ping").build();
        StreamObserver<PongResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(PongResponse value) {

            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
        assertThrows(RuntimeException.class, () -> pingGrpcController.ping(ping, responseObserver));
    }
}