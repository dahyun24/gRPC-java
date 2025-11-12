# 2025-2 경희대학교 풀스택서비스네트워킹 [FSSN] 
## gRPC 코드 java로 구현
### 01. hello_grpc
```
lec-07-prg-01-hello_gRPC/
├── build.gradle
├── src/main
│     └── java/com/
│     │     └── example/grpc/
│     │            ├── hello_grpc.java   // 원격으로 호출될 실제 연산 로직
│     │            ├── server.java
│     │            └── client.java
│     └── proto/
│          └── hello_grpc.proto          // 원격 호출 규격 정의 (interface + data 구조)

```
> 빌드 시 protoc(Protocol Buffers Compiler)이 자동으로 .proto를 분석해 다음과 같은 자바 파일을 생성
<img width="345" height="449" alt="스크린샷 2025-11-11 오후 5 24 05" src="https://github.com/user-attachments/assets/eaa218af-6c44-4493-91ff-8cbbc264a196" />

> 서버 실행
<img width="450" height="169" alt="스크린샷 2025-11-11 오후 10 01 04" src="https://github.com/user-attachments/assets/3e39d69b-7e71-486a-9c9d-0d7267906128" />

> 클라이언트 실행
<img width="681" height="193" alt="스크린샷 2025-11-11 오후 10 00 55" src="https://github.com/user-attachments/assets/7ffcb62a-475f-486d-911f-39f5c011f988" />
