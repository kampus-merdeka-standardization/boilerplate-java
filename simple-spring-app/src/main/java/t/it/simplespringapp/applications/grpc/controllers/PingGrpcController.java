package t.it.simplespringapp.applications.grpc.controllers;

import io.grpc.stub.StreamObserver;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.Disposable;
import t.it.simplespringapp.domains.services.PingService;
import t.it.simplespringapp.models.requests.Ping;
import t.it.simplespringapp.models.responses.Pong;
import t.it.simplespringapp.stubs.PingServiceStubGrpc;


@RequiredArgsConstructor
@GrpcService
public class PingGrpcController extends PingServiceStubGrpc.PingServiceStubImplBase {
    private final PingService pingService;
    private Disposable subscriber = null;

    @Override
    public void request(Ping request, StreamObserver<Pong> responseObserver) {
        subscriber = pingService.ping().subscribe(response -> {
                    Pong pong = Pong.newBuilder().setMessage(response).build();
                    responseObserver.onNext(pong);
                },
                responseObserver::onError,
                responseObserver::onCompleted
        );
    }

    @PreDestroy
    public void onDestroy() {
        if (subscriber != null)
            subscriber.dispose();
    }
}
