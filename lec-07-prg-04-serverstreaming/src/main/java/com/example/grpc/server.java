package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import serverstreaming.Message;
import serverstreaming.ServerStreamingGrpc;

import java.io.IOException;
import java.util.List;

public class server {

    static class ServerStreamingServicer extends ServerStreamingGrpc.ServerStreamingImplBase {

        @Override
        public void getServerResponse(serverstreaming.Number request,
                                      StreamObserver<serverstreaming.Message> responseObserver)
        {

            System.out.printf("Server processing gRPC server-streaming {%d}.%n", request.getValue());

            List<String> messages = List.of(
                    "message #1",
                    "message #2",
                    "message #3",
                    "message #4",
                    "message #5"
            );

            for (String msg : messages) {
                responseObserver.onNext(
                        Message.newBuilder().setMessage(msg).build()
                );
            }

            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(50051)
                .addService(new ServerStreamingServicer())
                .build()
                .start();

        System.out.println("Starting server. Listening on port 50051.");
        server.awaitTermination();
    }
}
