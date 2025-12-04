package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import serverstreaming.Number;
import serverstreaming.ServerStreamingGrpc;

public class client {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ServerStreamingGrpc.ServerStreamingBlockingStub stub =
                ServerStreamingGrpc.newBlockingStub(channel);

        Number request = Number.newBuilder()
                .setValue(5)
                .build();

        var responses = stub.getServerResponse(request);

        responses.forEachRemaining(msg ->
                System.out.println("[server to client] " + msg.getMessage())
        );

        channel.shutdown();
    }
}
