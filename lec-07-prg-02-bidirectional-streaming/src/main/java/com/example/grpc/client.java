package com.example.grpc;

import bidirectional.BidirectionalGrpc;
import bidirectional.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        BidirectionalGrpc.BidirectionalStub stub = BidirectionalGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<Message> requestObserver = stub.getServerResponse(
                new StreamObserver<>() {
                    @Override
                    public void onNext(Message msg) {
                        System.out.println("[server to client] " + msg.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error: " + t.getMessage());
                        latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Server completed.");
                        latch.countDown();
                    }
                }
        );

        // Python client와 동일하게 메시지 5개 전송
        String[] messages = {
                "message #1",
                "message #2",
                "message #3",
                "message #4",
                "message #5"
        };

        for (String msg : messages) {
            System.out.println("[client to server] " + msg);
            requestObserver.onNext(Message.newBuilder().setMessage(msg).build());
            Thread.sleep(300);
        }

        requestObserver.onCompleted();
        channel.shutdown();
    }
}

