package t.it.simplespringapp.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import t.it.simplespringapp.applications.services.PingService;
import t.it.simplespringapp.interfaces.models.responses.PongResponse;
import t.it.simplespringapp.models.requests.Ping;
import t.it.simplespringapp.models.responses.Pong;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@Slf4j
@SpringJUnitConfig(PingGrpcController.class)
class PingGrpcControllerTest {
    @Autowired
    private PingGrpcController pingGrpcController;

    @MockBean
    private PingService pingService;

    @DirtiesContext
    @Test
    void testPing() {
        Mockito.when(pingService.ping()).thenReturn(PongResponse.builder().message("pong").build());

        Ping ping = Ping.newBuilder().setMessage("ping").build();
        StreamObserver<Pong> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Pong value) {
                log.info("got response: " + value.getMessage());
                assertNotNull(value);
                assertEquals(Pong.newBuilder()
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
        pingGrpcController.request(ping, responseObserver);
    }

    @Test
    void testPing_ShouldThrowException() {
        Mockito.when(pingService.ping()).thenThrow(new RuntimeException("Fail to get response"));

        Ping ping = Ping.newBuilder().setMessage("ping").build();
        StreamObserver<Pong> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Pong value) {

            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
        assertThrows(RuntimeException.class, () -> pingGrpcController.request(ping, responseObserver));
    }
}