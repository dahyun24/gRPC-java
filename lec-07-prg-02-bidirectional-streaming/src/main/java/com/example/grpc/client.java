package com.example.grpc;

import bidirectional.BidirectionalGrpc;
import bidirectional.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        BidirectionalGrpc.BidirectionalStub stub = BidirectionalGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        List<String> responseBuffer = new ArrayList<>();

        StreamObserver<Message> requestObserver = stub.getServerResponse(
                new StreamObserver<>() {
                    @Override
                    public void onNext(Message msg) {
                        responseBuffer.add(msg.getMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("Error: " + t.getMessage());
                        latch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        for (String m : responseBuffer) {
                            System.out.println("[server to client] " + m);
                        }
                        latch.countDown();
                    }
                }
        );

        String[] messages = {"message #1", "message #2", "message #3", "message #4", "message #5"};

        for (String msg : messages) {
            System.out.println("[client to server] " + msg);
            requestObserver.onNext(Message.newBuilder().setMessage(msg).build());
        }

        requestObserver.onCompleted();

        latch.await();
        channel.shutdown();
    }
}
