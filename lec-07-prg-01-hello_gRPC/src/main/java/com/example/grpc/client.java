package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class client {
    public static void main(String[] args) {

        // (3) gRPC 통신 채널 생성
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // (4) Stub 생성
        MyServiceGrpc.MyServiceBlockingStub stub = MyServiceGrpc.newBlockingStub(channel);

        // (5) 요청 메시지 생성
        MyNumber request = MyNumber.newBuilder().setValue(4).build();

        // (6) 원격 함수 호출
        MyNumber response = stub.myFunction(request);

        // (7) 결과 출력
        System.out.println("gRPC result: " + response.getValue());

        // 채널 종료
        channel.shutdown();
    }
}

