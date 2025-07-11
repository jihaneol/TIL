<details>
<summary> 템플릿 </summary>
<br>
<pre>
무슨 차이일까?
</pre>

</details>

<details>
<summary> 1. 가상 스레드와 스레드의 차이를 설명 해주세요. </summary>
<br>
<pre>
스레드(전통적인 스레드), 가상 스레드(virtual thread)는 둘다 java에서 동시성을 다루기 위한 기술이지만,
구조와 성능 특성에 중요한 차이가 있다.

# 1. 정의
스레드
- os 커널 수준의 실제 스레드
- 오래전부터 존재
- os가 스레드를 스케줄링

가상 스레드
- 경량 스레드, 사용자 수준에서 관리
- java 19에서 프리뷰로 도입
- jvm이 직접 스케줄링

# 2. 스케줄릥
스레드는 os 커널이 직접 cpu 할당 하고
가상 스레드는 jvm 내부의 스케줄러가 플랫폼 스래드 위에서 매우 많은 수의 가상 스레드를 협력적으로 실행
## 플랫폼 스레드란?
java에서 생성된 일반적인 스레드 클래스 인스턴스 이며, 실제로는 os의 커널 스레드와 1:1 매핑이 된다.

java의 new Thread()로 만든 스레드는 곧바로 os 차원에서도 하나의 스레드로 생성된다.

# 3. 블로킹 I/O 처리
스레드
- 실제 os 스레드가 블록킹되어서 비효율
- 스레드 낭비 발생

가상 스레드
- jvm이 블로킹 감지 후 다른 가상 스레드로 스위칭
- cpu 리소스 활용 극대화

| 항목     | 전통 스레드    | 가상 스레드                 |
| ------ | --------- | ---------------------- |
| 실행 단위  | OS 커널 스레드 | 사용자 수준 스레드             |
| 스케줄링   | OS에서 수행   | JVM에서 수행               |
| 블로킹 처리 | 비효율적      | 효율적으로 감지 및 전환          |
| 스레드 수  | 제한적       | 수십만 개 가능               |
| 도입 시기  | 오래전부터     | Java 19+ (정식은 Java 21) |


</pre>

</details>

<details>
<summary> 2. 자바 21의 장점 </summary>
<br>
<pre>

가상 스레드
- 초경량 스레드 수천~수만개 생성 가능
- 비동기 코드보다 쉽게 동시성 처리 가능
- 블로킹 I/O 코드도 성능 저하 없이 작성 가능
- 웹서버, 마이크로서비스에 최적

레코드 패턴
- 패턴 매칭으로 더 간결한 코드 작성 가능
```java
if (obj instanceof Person(String name, int age)) {
    System.out.println(name + " is " + age + " years old");
}
```
스위치 패턴 매칭
- switch 문에서 instanceof 없이 타입 분기 가능
```java
static String formatShape(Shape s) {
    return switch (s) {
        case Circle c -> "Circle with radius " + c.radius();
        case Rectangle r -> "Rectangle: " + r.width() + " x " + r.height();
    };
}
```

성능 향상
- 가비지 컬렉터, JIT 컴파일러, 메모리 최적화 향상
- GC 감소, JIT 개선으로 처리 속도 향상

</pre>

</details>

<details>
<summary> 3. 자바 버전별 차이 </summary>
<br>
<pre>

| 기능/특징                            | Java 11 (2018)    | Java 17 (2021)       | Java 21 (2023) |
| -------------------------------- | ----------------- | -------------------- | -------------- |
| 🎯 **LTS 지원**                    | ✅                 | ✅                    | ✅              |
| 📦 `var`의 지역 변수 선언               | ✅ (Java 10)       | ✅                    | ✅              |
| 📦 텍스트 블록 (`"""`)                | ❌                 | ✅                    | ✅              |
| 📦 레코드 (`record`)                | ❌                 | ✅                    | ✅              |
| 🧩 패턴 매칭 for `instanceof`        | ❌                 | ✅                    | ✅              |
| 🧩 레코드 패턴                        | ❌                 | ❌                    | ✅ (정식)         |
| 🔁 Switch 패턴 매칭                  | ❌                 | 🔁 (프리뷰)             | ✅ (정식)         |
| 🔄 Sealed 클래스                    | ❌                 | ✅ (정식)               | ✅              |
| 🧵 가상 스레드 (Virtual Thread)       | ❌                 | 🔁 (프리뷰)             | ✅ (정식)         |
| 🧪 Structured Concurrency        | ❌                 | ❌                    | 🔁 (프리뷰)       |
| 📝 String Template (`STR."..."`) | ❌                 | ❌                    | 🔁 (프리뷰)       |
| ⚙️ ZGC (저지연 가비지 컬렉터)             | 실험적               | ✅ 개선                 | ✅ 안정화          |
| ⚙️ G1 GC 개선                      | ✅                 | ✅                    | ✅              |
| 🔒 TLS 1.3 지원                    | ✅                 | ✅                    | ✅              |
| 🧹 `HttpClient` 정식               | ✅                 | ✅                    | ✅              |
| 🧼 제거된 API                       | Java EE, CORBA 제거 | Applet 등 더 많은 API 제거 | 보안/호환성 개선 중심   |
| 💡 `instanceof` 개선               | ❌                 | ✅                    | ✅              |
| 💬 JEP 수                         | 약 17개             | 약 14개                | **15개 이상**     |

| 목적          | 추천 버전       | 이유                                          |
| ----------- | ----------- | ------------------------------------------- |
| 안정성과 호환성    | Java 11     | 가장 안정된 이전 LTS, 보수적인 프로젝트                    |
| 기능 + 안정성    | Java 17     | 레코드, 패턴 매칭 등 현대적 문법 포함                      |
| 최신 기능 적극 도입 | **Java 21** | 가상 스레드, 패턴 매칭, 구조적 동시성, String 템플릿 등 풍부한 개선 |

</pre>

</details>


<details>
<summary> 4. 가비지 컬랙터에 대해서 설명 해주세요. </summary>
<br>
<pre>
무슨 차이일까?
</pre>

</details>

<details>
<summary> 5. 동기 VS 비동기 VS 논블로킹 VS 블로킹  </summary>
<br>
<pre>

# 핵심 요약

| 구분    | 동기(Synchronous)      | 비동기(Asynchronous)                | 블로킹(Blocking)        | 논블로킹(Non-blocking) |
| ----- | -------------------- | -------------------------------- | -------------------- | ------------------ |
| 의미    | 결과를 기다렸다가 다음 작업      | 결과 안 기다리고 바로 다음 작업               | 결과가 올 때까지 **기다림**    | 결과 없어도 바로 **리턴**   |
| 흐름 제어 | 호출 → 응답 → 다음         | 호출 → 다음 → 응답 오면 콜백               | **스레드 점유** O         | **스레드 점유** X       |
| 예시    | `result = doTask()`  | `doTask(callback)`               | `read()`             | `poll()`           |
| 처리 방식 | 순차 처리                | 병렬/콜백 처리                         | 멈춰서 대기               | 응답 없으면 그냥 넘김       |
| 대표 도구 | `RestTemplate`, JDBC | `CompletableFuture`, `WebClient` | `InputStream.read()` | `NIO`, `Netty`     |


| 조합           | 설명             | 예시                                    |
| ------------ | -------------- | ------------------------------------- |
| ✅ 동기 + 블로킹   | 결과 기다림, 스레드 점유 | 대부분의 전통적인 코드 (`JDBC`, `RestTemplate`) |
| ✅ 동기 + 논블로킹  | 거의 없음          | 드뭄, 예외적 상황                            |
| ✅ 비동기 + 블로킹  | 드물지만 가능        | 비동기 호출 후 `.get()` 같은 blocking 메서드     |
| ✅ 비동기 + 논블로킹 | 진짜 고성능 구조      | `WebClient`, `Netty`, `Reactor` 등     |


- 동기 + 블로킹
```java
String response = restTemplate.getForObject("/api", String.class);
System.out.println("응답: " + response);

```
스레드가 점유 된다.
- 비동기 + 블로킹
```java
Future<String> future = executor.submit(() -> callApi());
String result = future.get(); // ❌ 블로킹

```
- 동기 + 논 블로킹
거의 드문 조합
```java
int available = socketChannel.read(buffer); // 즉시 리턴
// 결과 없으면 -1, 기다리지 않음
```
논블로킹이지만 비동기적으로는 처리 안된다.
호출자가 직접 계속 체크해야 된다.(polling)

- 비동기 + 논 블로킹 -> 가장 효율적
```java
webClient.get()
    .uri("/api")
    .retrieve()
    .bodyToMono(String.class)
    .subscribe(result -> {
        System.out.println("결과: " + result);
});

```
요청 후 스레드 점유 ❌
결과는 나중에 콜백으로 옴
진짜 비동기 + 논블로킹

| 개념        | 설명                                   |
| --------- | ------------------------------------ |
| **비동기**   | 흐름 제어 중심 (다음 작업으로 넘어감)               |
| **논블로킹**  | 자원 사용 중심 (스레드 점유하지 않음)               |
| 같이 쓰이는 이유 | 비동기 + 논블로킹 조합이 **최고의 리소스 효율**을 내기 때문 |


# 내 정리
동기방식은 결과를 기다렸다가 다음 작업을 하고
비동기는 기다리지 않고 다음 작업을 진행
논블로킹은 스레드를 점유하지 않고 응답이 오면 콜백처리
블로킹은 결과가 올때 까지 기다리고 스레드를 점유한다.
</pre>

</details>

<details>
<summary> 자바에서 Checked Exception과 Unchecked Exception에 대해서 설명해주세요</summary>
<br>
<pre>

Checked Exception은 컴파일 시점에 확인되며, 반드시 처리해야 하는 예외입니다. 자바에서는 IOException, SQLException 등이 이에 속합니다. 
Checked Exception을 유발하는 메서드를 호출하는 경우, 메서드 시그니처에 throws를 사용하여 호출자에게 예외를 위임하거나 메서드 내에서 
try-catch를 사용하여 해당 예외를 반드시 처리해야합니다.

Unchecked Exception은 런타임 시점에 발생하는 예외로, 컴파일러가 처리 여부를 강제하지 않습니다. 자바에서는 RuntimeException을 상속한 
예외들이 해당됩니다. 일반적으로 프로그래머의 실수나 코드 오류로 인해 발생합니다.

# 각각 언제 사용해야 할까요?
Checked Exception은 외부 환경과의 상호작용에서 발생할 가능성이 높은 예외에 적합합니다. 예를 들어, 파일 입출력, 네트워크 통신 등에서 발생할 수 
있는 예외는 Checked Exception으로 처리하는 것이 좋습니다. 이러한 예외는 예측 가능하며, 호출하는 쪽에서 적절히 처리할 수 있는 여지가 있습니다.

Uncheked Exception은 코드 오류, 논리적 결함 등 프로그래머의 실수로 인해 발생할 수 있는 예외에 적합합니다. 예를 들어, null 참조 또는 잘못된 
인덱스 접근 등은 호출자가 미리 예측하거나 처리할 수 없기 때문에 Unchecked Exception으로 두는 것이 좋습니다.
</pre>

</details>

<details>
<summary> 일급 컬렉션이 무엇인가요?</summary>
<br>
<pre>

 일급 컬렉션은 하나의 컬렉션을 감싸는 클래스를 만들고, 해당 클래스에서 컬렉션과 관련된 비즈니스 로직을 관리
하는 패턴을 말합니다. 

# 일급 컬렉션을 사용해야 하는 이유는?

일급 컬렉션 클래스에 로직을 포함하거나 비즈니스에 특화된 명확한 이름을 부여할 수 있습니다. 또한, 불필요한 컬렉션 API를 외부로 노출하지 않도록 
할 수 있으며, 컬렉션을 변경할 수 없도록 만든다면 예기치 않은 변경으로부터 데이터를 보호할 수 있습니다.

</pre>

</details>
