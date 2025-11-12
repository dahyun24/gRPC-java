package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;

import static com.example.grpc.hello_grpc.my_func;

public class server {
    // (4) MyServiceServicer 클래스 정의 (Python의 MyServiceServicer와 동일)
    static class MyServiceServicer extends MyServiceGrpc.MyServiceImplBase {

        // (5) 서버 클래스에 원격 호출될 함수에 대한 rpc 함수를 작성
        @Override
        public void myFunction(MyNumber request, StreamObserver<MyNumber> responseObserver) {

            // (5.2) 응답 메시지 객체 생성
            MyNumber.Builder response = MyNumber.newBuilder();

            // (5.3) hello_grpc.my_func() 호출
            int result = my_func(request.getValue());
            response.setValue(result);

            // (5.4) 응답 전송
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        }
    }

    // (6) gRPC 서버 구동
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new MyServiceServicer())
                .build()
                .start();

        System.out.println("Starting server. Listening on port 50051.");

        // (9) grpc.server가 유지되도록 프로그램 실행을 유지함
        server.awaitTermination();
    }

}
