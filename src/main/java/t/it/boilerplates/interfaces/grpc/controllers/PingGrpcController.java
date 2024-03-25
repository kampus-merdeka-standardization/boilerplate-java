package t.it.boilerplates.interfaces.grpc.controllers;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import t.it.boilerplates.PingControllerGrpc;
import t.it.boilerplates.PingRequest;
import t.it.boilerplates.PongResponse;
import t.it.boilerplates.applications.services.PingService;


@RequiredArgsConstructor
@GrpcService
public class PingGrpcController extends PingControllerGrpc.PingControllerImplBase {
    private final PingService pingService;

    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        responseObserver.onNext(pingService.ping());
        responseObserver.onCompleted();
    }
}
