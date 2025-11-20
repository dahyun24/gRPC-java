package com.example.grpc;

import bidirectional.BidirectionalGrpc;
import bidirectional.Message;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class server {

    static class BidirectionalService extends BidirectionalGrpc.BidirectionalImplBase {

        @Override
        public StreamObserver<Message> getServerResponse(StreamObserver<Message> responseObserver) {

            System.out.println("Server processing gRPC bidirectional streaming.");

            return new StreamObserver<>() {
                @Override
                // 클라이언트로부터 메시지를 받을 때마다 실행
                public void onNext(Message msg) {
                    // 클라이언트가 보낸 메시지를 그대로 echo
                    responseObserver.onNext(msg);
                }

                @Override
                // 스트림 처리 중 오류가 발생했을 때 실행
                public void onError(Throwable t) {
                    t.printStackTrace();
                }

                @Override
                // 클라이언트가 스트림 전송을 완료했을 때 실행
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new BidirectionalService())
                .build()
                .start();

        System.out.println("Starting server. Listening on port 50051.");
        server.awaitTermination();
    }
}
