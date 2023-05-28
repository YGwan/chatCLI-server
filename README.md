# ChatGPT CLI Command

<br>

### 프로젝트 주제

- CLI 환경(터미널/리눅스 환경)에서 원활하게 chatgpt를 사용할 수 있는 서버 & 명령어 개발

<br>

### 프로젝트 목적

- open ai api가 cli환경에서 제공하는 api의 요청 형식이 너무 복잡하다.
- open ai api에 보낸 요청에 대한 응답 형식이 내가 원하는 질문에 대한 답을 한눈에 보기 힘들다.
- 이를 사용자 편의성을 고려해 쉽게 사용할 수 있는 명령어를 만들자 (chatgpt 사용을 추상화시켜보자)

<br>

### 기대 효과
- open ai api의 복잡한 요청 형식을 알 필요가 없다.
  - header, body 형식 등
- cli환경에서 편하게 open ai api를 사용할 수 있다.
- open ai ap의 복잡한 응답 형식을 정답만 한눈에 보기 편하다.

<br>

### 프로젝트 구조
<img width="981" alt="스크린샷 2023-05-28 오전 1 42 21" src="https://github.com/YGwan/spring-chatgpt-communication/assets/50222603/3ea07427-4872-4406-aab4-6c0bc5255102">

1. CLI ENV
    - 사용자가 원하는 명령어를 실행하는 환경이다.
    - 질문 형식의 요청을 보내면 질문에 대한 응답을 보여준다.
    - UI 역할

<br>

2. Spring Server
    - 사용자가 요청한 질문을 받고 이를 OpenAi Server가 원하는 형식의 요청 패킷으로 파싱한다.(OpenAiRequestEntity class)
    - 서버에서 보낸 OpenAi Server 요청에 대한 응답을 사용자가 보기 편한 응답 패킷으로 파싱한다.(OpenAiResponseEntity class)

<br>

3. OpenAi Server
    - 실제 Open Ai에서 운영하는 서버이다.(chatGPT 서비스를 사용할 수 있는 서버)
    - 해당 서버에서는 여러 서비스에 대한 api를 제공한다.
    - [해당 서버 주소](https://platform.openai.com/)에서 더 많은 api를 확인할 수 있다.


<br>

### 핵심 기술

1. ObjectMapper
    - JSON 형식을 사용할 때, 응답들을 직렬화하고 요청들을 역직렬화 할 때 사용하는 기술이다.
    - 즉, JSON 데이터와 Java 객체 간의 변환을 수행하는 기능을 제공한다.
    - Jackson 라이브러리의 클래스이다.
    - JSON 데이터를 Java 객체로 변환할 때는, ObjectMapper 클래스의 readValue() 메서드를 사용한다.
    - Java 객체를 JSON 데이터로 변환할 때는, ObjectMapper 클래스의 writeValue() 메서드를 사용한다.

<br>

2. ResTemplate
    - RESTful 웹 서비스에 대한 HTTP 요청을 보내고 응답을 받아야 할때 사용하는 기술이다.
    - 즉, 외부 api를 spring에서 사용할때 해당 서버에 요청을 보내고 요청에 대한 응답을 받아야 할때 사용하는 기술이다.
    - RestTemplate은 간편한 방식으로 HTTP 요청을 만들고 처리할 수 있도록 다양한 메서드를 제공한다.(GET, POST, PUT, DELETE 등)
    - 요청에 필요한 헤더, 매개변수, 본문 등을 설정할 수 있다. 
    - 응답을 받을 때는 응답의 상태 코드, 헤더, 본문 등을 확인할 수 있다.
    - 후에 해당 기술 대신 WebClient를 사용한 기술도 추가할 예정이다.(Spring 5부터는 RestTemplate 대신 WebClient를 사용하는 것을 권장하기 때문에)

<br>

3. chatgpt 질문 세션 유지 방식
    - chatgpt에 질문을 하면 CLI 환경에서는 전 질문 내용이 다음 질문과 연결되지 않는 문제가 발생한다.
    - 방법 1 : 질문을 연결한다. -> 질문에 대한 답이 연결이 안되서 이 또한 정상적으로 작동하지 않는다.
    - 방법 2 : 질문 + 해당 질문에 대한 답을 연결한다. -> 정상적으로 작동되는 것을 확인할 수 있다.
    - ```java
       public ResponseEntity<String> chat(@RequestBody String request) throws JsonProcessingException {
            chatRequest = chatRequest + request; // 이전 질문(chatRequest)을 현재 질문 request에 연결한다.
            HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(chatRequest); // open ai api 요청 패킷을 만든다.
            ResponseEntity<String> openAiResponseEntity = this.openAiResponseEntity.chatParsed(openAiRequest); // 위에서 만든 패킷으로 open ai api에 요청을 보낸 후 결과값을 받아온다.(답만 파싱해서 받는다.)
            chatRequest = chatRequest + openAiResponseEntity.getBody(); // 답 또한 질문과 연결한다.
            return openAiResponseEntity;
        }

<br>



