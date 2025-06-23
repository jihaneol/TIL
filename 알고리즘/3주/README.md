# 해시
해시 함수를 사용해서 변환한 값을 인덱스로 삼아 키와 값을 저장해서 빠른 데이터 탐색을
제공하는 자료구조

key value로 동작한다.

## 특징
1. 해시는 단방향으로 동작 (key로 찾고 value로는 못찾는다.)
2. O(1)의 접근 속도
3. 값을 인덱스로 활요하려면 적절한 변환 과정을 거쳐야 한다.

해시를 사용하면 순차 탐색없이 해시 함수를 활용해 특정 값이 있는 위치를 바로 찾을 수 있다.

## 구조
키 -> 해시 함수 -> 해시 테이블(키와 대응한 값이 저장)

해시 테이블의 각 데이터를 버킷이라고 한다.

## 해시를 활용한 분야
비밀번호 관리, 데이터베이스 인덱싱, 블록체인

# 해시 함수
## 구현 고려
1. 함수가 변환한 값은 해시 테이블의 크기를 넘으면 안된다.
2. 해시 함수가 변환한 값은 최대한 적게 발생해야한다. (비둘기집 이론)

# 충돌 처리
해시 함수의 결과값이 같으면 충돌이라한다.


## 체이닝으로 처리
충돌 발생시 해당 버킷에 링크드 리스트로 같은 해시값을 가지는 데이터를 연결한다.

### 체이닝 단점
1. 해시 테이블 공간 활용성 하락
2. 검색 성능이 하락

검색 성능은 HashMap 클래스는 체이닝을 사용하는데 링크드리스트의 데이터가 일정 개수가 넘어가면
탐색 트리로 변환하여 데이터에 접근하여 성능을 개선한다.

## 개방 주소법으로 처리
충돌시 빈 버킷을 찾아 충돌값을 삽입한다.

### 선형 탐사 방식
충돌 발생하면 다른 빈 버킷을 찾을 때까지 일정한 간격으로 이동

한칸씩 이동시 특정 영역에 해시 충돌 발생 값이 모이는 영역(클러스터)를 형성하는데 이를 방지하기 위해 
제곱수만큼 이동하는 탐사 방법도 있다.

## 이중 해싱 방식
해시 함수를 2개 사용하는 방법

# 문제 18 두 개의 수로 특정값 만들기

## 문제 분석
해시를 활용해서 찾기.

value 값을 hashSet에 넣으면서 target-value가 hashSet에 들어있는지 확인한다.

처음에는 투 포인터로 찾으려고 했다.

```java
hashset = new hashset();

for(int i: arr){
        if(hashset.contains(target-i)){
            return true;
        }
        hashset.add(i);
}

return false;
```

# 문제 19 완주하지 못한 선수

https://school.programmers.co.kr/learn/courses/30/lessons/42576

```java
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> map = new HashMap();
        for(String c : completion){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        
        for(String p : participant){
            if(map.getOrDefault(p,0)==0) return p;
            map.put(p, map.get(p)-1);
        }
        
        return answer;
    }
}  
```
89번 줄에 getOrDefault를 사용함으로써 불필요한 코드를 줄일 수 있었다.

# 문제 20 할인 행사
https://school.programmers.co.kr/learn/courses/30/lessons/131127

```java
import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // want 값 map에 넣기
        Map<String, Integer> map = new HashMap();
        for(int i=0; i<want.length; i++){
            map.put(want[i], number[i]);
        }
        
        for(int i=0; i<discount.length-9; i++){
            Map<String, Integer> target = new HashMap();
            
            for(int j=i; j<i+10; j++){
                target.put(discount[j], target.getOrDefault(discount[j],0)+1);
            }
            
            if(map.equals(target)) 
                answer++;
            
        }
        
        return answer;
    }
}
```

map의 equals로 간단하게 target과 비교 가능하다.