package t.it.simplespringapp.applications.grpc.controllers;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.Disposable;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.models.responses.MetaResponse;
import t.it.simplespringapp.models.responses.WebResponse;
import t.it.simplespringapp.services.PingServiceGrpc;

@RequiredArgsConstructor
@GrpcService
@Slf4j
public class PingGrpcController extends PingServiceGrpc.PingServiceImplBase {
    private final PingService pingService;
    private Disposable subscriber = null;


    @PreDestroy
    public void onDestroy() {
        if (subscriber != null)
            subscriber.dispose();
    }

    @Override
    public void ping(Empty request, StreamObserver<WebResponse> responseObserver) {
        subscriber = pingService.ping().subscribe(
                response -> {
                    MetaResponse metaResponse = MetaResponse.newBuilder().setCode(String.valueOf(Status.OK.getCode().value())).setMessage(Status.OK.getCode().toString()).build();
                    WebResponse webResponse = WebResponse.newBuilder().setData(response).setMeta(metaResponse).build();
                    responseObserver.onNext(webResponse);
                },
                responseObserver::onError,
                responseObserver::onCompleted
        );
    }
}
