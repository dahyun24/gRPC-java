package com.example.grpc;

import clientstreaming.ClientStreamingGrpc;
import clientstreaming.Message;
import clientstreaming.Number;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class server {

    static class ClientStreamingServicer extends ClientStreamingGrpc.ClientStreamingImplBase {

        @Override
        public StreamObserver<Message> getServerResponse(StreamObserver<Number> responseObserver) {

            System.out.println("Server processing gRPC client-streaming.");

            return new StreamObserver<Message>() {

                int count = 0;

                @Override
                public void onNext(Message msg) {
                    count++;
                }

                @Override
                public void onError(Throwable throwable) {
                    throwable.printStackTrace();
                }

                @Override
                public void onCompleted() {
                    Number response = Number.newBuilder()
                            .setValue(count)
                            .build();

                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(50051)
                .addService(new ClientStreamingServicer())
                .build()
                .start();

        System.out.println("Starting server. Listening on port 50051...");

        server.awaitTermination();
    }
}
