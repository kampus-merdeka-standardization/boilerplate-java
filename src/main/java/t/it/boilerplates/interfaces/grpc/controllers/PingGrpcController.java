package t.it.boilerplates.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import t.it.boilerplates.applications.services.PingService;
import t.it.boilerplates.controllers.PingControllerGrpc;
import t.it.boilerplates.models.requests.Ping;
import t.it.boilerplates.models.responses.Pong;


@RequiredArgsConstructor
@GrpcService
public class PingGrpcController extends PingControllerGrpc.PingControllerImplBase {
    private final PingService pingService;

    @Override
    public void request(Ping request, StreamObserver<Pong> responseObserver) {
        final var ping = pingService.ping();
        final var pongResp = Pong.newBuilder()
                .setMessage(ping.message())
                .build();
        responseObserver.onNext(pongResp);
        responseObserver.onCompleted();
    }
}
