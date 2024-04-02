package t.it.simplespringapp.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import t.it.simplespringapp.applications.services.PingService;
import t.it.simplespringapp.controllers.PingControllerGrpc;
import t.it.simplespringapp.models.requests.Ping;
import t.it.simplespringapp.models.responses.Pong;


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
