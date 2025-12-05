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


---

### 02. bidirectional-streaming
> gRPC 양방향 스트리밍을 사용하여 클라이언트가 보낸 5개의 메시지를 서버가 실시간으로 받아서 그대로 클라이언트에게 되돌려 주는(Echo) 프로그램
```
lec-07-prg-02-bidirectional-streaming/
├── build.gradle
├── src/main
│     └── java/com/
│     │     └── example/grpc/
│     │            ├── server.java
│     │            └── client.java
│     └── proto/
│          └── bidirectional.proto       // 원격 호출 규격 정의 (interface + data 구조)

```
> 서버 실행
<img width="552" height="178" alt="스크린샷 2025-11-19 오전 12 08 46" src="https://github.com/user-attachments/assets/0b70322d-ec85-442f-a362-a31ca4453679" />


> 클라이언트 실행
<img width="483" height="299" alt="image" src="https://github.com/user-attachments/assets/d7848a34-4d18-4860-bf0f-afe97ce6c9af" />

---

### 03. clientstreaming
> 클라이언트가 Message를 여러 개 연속으로 서버에 스트림 형식으로 보내고, 전송이 끝나면 서버가 최종 결과를 단 한 번만 응답하는 방식
```
lec-07-prg-03-clientstreaming/
├── build.gradle
├── src/main
│     └── java/com/
│     │     └── example/grpc/
│     │            ├── server.java
│     │            └── client.java
│     └── proto/
│          └── clientstreaming.proto       // 원격 호출 규격 정의 (interface + data 구조)

```
> 서버 실행
<img width="486" height="153" alt="스크린샷 2025-11-27 오전 9 55 32" src="https://github.com/user-attachments/assets/0220b8ce-4676-438d-8478-19ad566a4cf0" />

> 클라이언트 실행
<img width="486" height="246" alt="스크린샷 2025-11-27 오전 9 55 06" src="https://github.com/user-attachments/assets/992095cd-3a03-4561-af77-235908e86d55" />

---

### 04. serverstreaming
> 클라이언트가 요청 1번만 보내고, 서버는 여러 개의 응답을 순차적으로 스트림 형태로 보내는 방식
```
lec-07-prg-04-serverstreaming/
├── build.gradle
├── src/main
│     └── java/com/
│     │     └── example/grpc/
│     │            ├── server.java
│     │            └── client.java
│     └── proto/
│          └── serverstreaming.proto       // 원격 호출 규격 정의 (interface + data 구조)

```
> 서버 실행
<img width="485" height="148" alt="스크린샷 2025-12-04 오후 8 48 46" src="https://github.com/user-attachments/assets/d761a2d3-87ae-4894-8635-31ccb411df76" />


> 클라이언트 실행
<img width="488" height="221" alt="스크린샷 2025-12-04 오후 8 49 05" src="https://github.com/user-attachments/assets/7f53da28-9c28-42b5-a3f3-1f542d6bec65" />

