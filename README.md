# ChatGPT CLI Command

<br>

![img](https://github.com/YGwan/spring-chatgpt-communication/assets/50222603/6ad14ef7-3aa6-4d09-a7d8-200c5a47369f)

---

<br>

## Project Topic

- Develop servers & commands that enable use of chatgpt in CLI environments (Terminal/Linux environments)


<br>

## Project Purpose

- The API request format provided by open API in cli environment is too complicated.
- The response format to the request sent to open ai api is hard to see at a glance the answer to the question I want.
- Let's make an easy-to-use command considering user convenience. (Let's abstract the use of chatgpt)

<br>

## Expectation effect
- You do not need to know the complex request format of open ai api.
  - Header, Body format etc..
- You can use open ai api conveniently in the cli environment.
- It is easy to see the complex response format of open aiap at a glance.

<br>

## How to run it

```shell
git clone https://github.com/YGwan/spring-chatgpt-communication.git
cd spring-chatgpt-communication/shell
source chatgptCommand.sh 
```

- User's guide
  - [English Version](https://github.com/YGwan/spring-chatgpt-communication/blob/main/docs/Usage_English.pdf)
  - [Korean Version](https://github.com/YGwan/spring-chatgpt-communication/blob/main/docs/Usage_Korean.pdf)

<br>

- Developer's guide
  - [English Version](https://github.com/YGwan/spring-chatgpt-communication/blob/main/docs/Develop_English.pdf)
  - [Korean Version](https://github.com/YGwan/spring-chatgpt-communication/blob/main/docs/Develop_Korean.pdf)

<br>

## Project Structure
<img width="981" alt="스크린샷 2023-05-28 오전 1 42 21" src="https://github.com/YGwan/spring-chatgpt-communication/assets/50222603/3ea07427-4872-4406-aab4-6c0bc5255102">

1. CLI ENV
    - It is an environment in which the user executes the desired commands.
    - Send a request in question format and show the response to the question.
    - UI Roles

<br>

2. Spring Server
    - ake the questions requested by the user and parse them into request packets in the format desired by the OpenAi Server. 
      - OpenAiRequestEntity class
    - Parse the response to an OpenAi Server request sent by the server into a user-friendly response packet. 
      - OpenAiResponseEntity class


<br>

3. OpenAi Server
    - It is actually a server operated by Open Ai.(Server with chatGPT service)
    - The server provides api for several services.
    - More api can be found at [the corresponding address](https://platform.openai.com/) 


<br>

## technology of use

<br>

Springboot, JPA, ResTemplate, Shell Script, MySQL, Swagger

<br>

## Core Technology

<br>

1. ObjectMapper
    - It is a technique used to serialize responses and deserialize requests when using the JSON format.
    - That is, it provides a function of performing conversion between JSON data and Java objects.
    - Class of Jackson Library.
    - When converting JSON data to Java objects, use the readValue() method of the ObjectMapper class.
    - When converting Java objects into JSON data, use the writeValue() method of the ObjectMapper class.

<br>

2. ResTemplate
    - Technology used to send HTTP requests for RESTful web services and receive responses.
    - In other words, it is a technology that is used when an external API is used in the spring and a request is sent to the server and a response to the request is required.
    - RestTemplate provides a variety of methods for creating and processing HTTP requests in an easy way.(GET, POST, PUT, DELETE etc..)
    - Headers, parameters, body, etc. required for the request can be set.
    - When receiving a response, the status code, header, and body of the response can be checked.
    - In the future, we will also add technology using WebClient instead of that technology. (Spring 5: RestTemplate Web Client)

<br>

3. Chatgpt Question Session Maintenance Method
    - When you ask a question on chatgpt, there is a problem in the CLI environment that the content of the previous question is not connected to the next question.
    - Method 1: Connect the questions. -> This also does not work normally because the answer to the question is not connected.
    - Method 2: Connect questions + answers to those questions. -> It works normally.
    
    <br>
    
      ```java
       public ResponseEntity<String> chat(@RequestBody String request) throws JsonProcessingException {
          chatRequest = chatRequest + request; // 이전 질문(chatRequest)을 현재 질문 request에 연결한다.
          HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(chatRequest); // open ai api 요청 패킷을 만든다.
          ResponseEntity<String> openAiResponseEntity = this.openAiResponseEntity.chatParsed(openAiRequest); // 위에서 만든 패킷으로 open ai api에 요청을 보낸 후 결과값을 받아온다.(답만 파싱해서 받는다.)
          chatRequest = chatRequest + openAiResponseEntity.getBody(); // 답 또한 질문과 연결한다.
          return openAiResponseEntity;
       }
      ```

<br>

4. Open ai api server communication(CORS Policy)
    <br>
    <img width="842" alt="스크린샷 2023-06-04 오전 9 20 16" src="https://github.com/YGwan/spring-chatgpt-communication/assets/50222603/bc04662d-a026-4d48-8995-4ec052f1a475">
    <br>
    - By default, OpenAI servers are open to HTTPS communication.
    - So if you want to communicate using HTTP, you cannot communicate with the openAI server.
    - However, since the OpenAI server allowed LOCALHOST communication, it is possible to communicate using LOCALHOST.(LOCALHOST uses HTTP by default)

<br>

## Supported Commands

| Command | Command Format | Description |
|--------|----------------|-----------------------------|
|Help|chatAsk-help|Show user manual.|
|Ask questions |chatAsk -m "Content of questions" |chatgpt and show the answers to the questions.|
|End of session|chatAsk-clear|End of question session (provides refresh).)|
|Grammar Check |chatAsk -gc "Question Content" |Grammar Check for English Sentences. Correct the sentence if the grammar is wrong or show the sentence as it is.|
|Voice conversion |chatAsk -at voice file.m4a|When a voice file is sent, the contents of the voice file are displayed in text format.|
|Emotional Analysis |chatAsk -md "Question Content" |Emotional Analysis for Sentences. Emotional Analysis is classified into three categories: negative, positive, and netal.|
|Sentence Translation |chatAsk -t "Question Contents" | English sentences are translated into Korean and shown.|
|Sentence Summary |chatAsk -md "Question Content" |Shows a short summary of the long sentence.|

<br>

## Supported ENV(OS) 

- MacOS
- Ubuntu
- Linux

If you only modify the shell script to the correct format for that OS, it works fine for other OS.

<br>

## version
- Spring Boot: 2.7.3
- java : 11


<br>
