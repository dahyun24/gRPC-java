package com.example.grpc;


import clientstreaming.ClientStreamingGrpc;
import clientstreaming.Message;
import clientstreaming.Number;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ClientStreamingGrpc.ClientStreamingStub asyncStub =
                ClientStreamingGrpc.newStub(channel);

        StreamObserver<Message> requestObserver =
                asyncStub.getServerResponse(new StreamObserver<Number>() {

                    @Override
                    public void onNext(Number number) {
                        System.out.println("[server to client] " + number.getValue());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onCompleted() {
                    }
                });

        // Python generate_messages()
        String[] msgs = {
                "message #1", "message #2", "message #3", "message #4", "message #5"
        };

        for (String m : msgs) {
            System.out.println("[client to server] " + m);
            Message msg = Message.newBuilder()
                    .setMessage(m)
                    .build();
            requestObserver.onNext(msg);
            Thread.sleep(200);
        }

        requestObserver.onCompleted();

        Thread.sleep(1000);

        channel.shutdown();
    }
}

