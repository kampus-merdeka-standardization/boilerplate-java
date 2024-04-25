package t.it.simplespringapp.applications.grpc.controllers;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Mono;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.models.responses.MetaResponse;
import t.it.simplespringapp.models.responses.WebResponse;

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
    void testPing_Success() {
        Mockito.when(pingService.ping()).thenReturn(Mono.just("pong"));

        MetaResponse metaResponse = MetaResponse.newBuilder().setCode(String.valueOf(Status.OK.getCode().value())).setMessage(Status.OK.getCode().toString()).build();
        WebResponse expectedWebResponse = WebResponse.newBuilder().setMeta(metaResponse).setData("pong").build();

        StreamObserver<WebResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(WebResponse value) {
                log.info("got response: {}", value.getData());
                assertNotNull(value);
                assertEquals(expectedWebResponse, value);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
        pingGrpcController.ping(Empty.newBuilder().build(), responseObserver);
    }

    @Test
    void testPing_Fail() {
        Mockito.when(pingService.ping()).thenThrow(new RuntimeException("Fail to get response"));

        StreamObserver<WebResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(WebResponse value) {

            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {

            }
        };
        assertThrows(RuntimeException.class, () -> pingGrpcController.ping(Empty.newBuilder().build(), responseObserver));
    }
}