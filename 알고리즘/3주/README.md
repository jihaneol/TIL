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

# 문제 21 오픈채팅방
https://school.programmers.co.kr/learn/courses/30/lessons/42888

## 문제 분석
1. q에 해당 명령어 앞글자랑 유저아이디를 넣는다.
2. q에서 빼오면서 e면 님이 들어왔습니다. 로 answer에 넣는다.
3. map에 유저아이디, 닉네임 을 저장한다.

## 코드
```java
import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList();
        Queue<String> q = new LinkedList();
        Map<String, String> userMap = new HashMap();
        for(String r : record){
            String[] split = r.split(" ");
            
            if("Enter".equals(split[0])){
                q.add('e'+split[1]);
                userMap.put(split[1], split[2]);
            }
            else if("Leave".equals(split[0])){
                q.add('l'+split[1]);
            }
            else{
                userMap.put(split[1], split[2]);
            }
        }
        
        while(!q.isEmpty()){
            String s = q.poll();
            String nickname = userMap.get(s.substring(1));
            if('e'==s.charAt(0)){
                answer.add(nickname+"님이 들어왔습니다.");
            }
            else{
                answer.add(nickname+"님이 나갔습니다.");
            }
        }
        
        return answer.toArray(String[]::new);
    }
}
```

q를 사용하지 않아도 된다.

다시한번 record를 돌려서 읽으면 되기 때문이다.

또한, enter와 change에 한해서만 userMap.put()을 하면된다...

# 문제 22 베스트 앨범

## 문제 분석
1. 장르, 총합을 담는 map 만들고 정렬
2. 장르, 앨범리스트를 담는 map 만든다.
3. 장르 총합 map을 forEach로 빼와서 장르앨범리스트의 map에서 앨범리스트 sorted limit(2) 해서 answer에 고유번호를 담는다.

## 코드
```java
import java.util.*;

class Album{
    int idx, play;
    Album(int i, int p){
        idx = i;
        play = p;
    }
}
class BestAlbum{
    int play;
    List<Integer> values;
    BestAlbum(int p, List<Integer> v){
        play   = p;
        values = v;
    }
    public List<Integer> getValues(){
        return this.values;
    }

}
class Solution {
    public int[] solution(String[] genres, int[] plays) {

        Map<String, List<Album>> genreMap = new HashMap();
        List<Integer> answer = new ArrayList();

        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int play = plays[i];

            if(!genreMap.containsKey(genre)){
                genreMap.put(genre, new ArrayList());
            }
            genreMap.get(genre).add(new Album(i, play));

        }

        List<BestAlbum> bestAlbums = new ArrayList();

        genreMap.entrySet().forEach(entry ->{
            String genre = entry.getKey();
            List<Album> albums = genreMap.get(genre);

            if(albums.size()==1){
                bestAlbums.add(new BestAlbum(albums.get(0).play, Arrays.asList(albums.get(0).idx)));
            }
            else{
                // 정렬
                albums.sort((a, b) -> {
                    return a.play==b.play? Integer.compare(a.idx, b.idx) : Integer.compare(b.play, a.play);
                });

                bestAlbums.add(new BestAlbum(albums.stream().mapToInt(a1->a1.play).sum(),
                        Arrays.asList(albums.get(0).idx, albums.get(1).idx)));
            }
        });

        // 베스트 앨범 정렬
        bestAlbums.sort((a,b) -> Integer.compare(b.play, a.play));

        for(BestAlbum ba : bestAlbums){
            answer.addAll(ba.getValues());
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

## stable sort
같은 값을 가지는 원소들의 상대적 순서를 유지하는 정렬 알고리즘.

map의 entrySet(), stream(), sorted() 메소드는 순서가 있는 스트림을 정렬할 때는 기존 순서가 유지되는 stable sort로 정렬됩니다.

즉, genreMap의 value인 ArrayList는 인덱스 0부터 n까지 순서가 있는 컬렉션이고 이를 스트림으로 변환하면 순서가 있는 ordered 스트림이 됩니다.

ordered 스트림을 sorted 메서드로 정렬하면 정렬 기준의 값이 같은 경우 기존 순서를 유지합니다.

| 메서드                              | 안정 정렬 여부           |
| -------------------------------- | ------------------ |
| `Collections.sort(List)`         | ✅ Stable (TimSort) |
| `List.stream().sorted(...)`      | ✅ Stable           |
| `Arrays.sort(Object[])`          | ✅ Stable           |
| `Arrays.sort(int[])`, etc. (기본형) | ❌ Unstable         |


# 문제 23 신고 결과 받기
https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=java
## 문제 분석
1. 신고자가 k명 이상인지 확인
2. 신고자가 k명 이상이면 신고자의 결과 통보 메일 수신 횟수 +1

## 코드
```java
import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        /**
        신고된 유저, 신고당한 횟수
        유저, 유저가 신고한 id
        
        각 유저가 받은 메일 수 return
        **/
        
        int[] answer = new int[id_list.length];
        Map<String, Integer> banMap = new HashMap();
        Map<String, List<String>> userMap = new HashMap();
        Set<String> set = new HashSet();
        // 신고하기
        for(String r : report){
            if(set.contains(r)){
                continue;
            }
            set.add(r);
            String[] split = r.split(" ");
            String user = split[0];
            String banId = split[1];
            
            banMap.put(banId, banMap.getOrDefault(banId, 0)+1);
            if(!userMap.containsKey(user)){
                userMap.put(user, new ArrayList());
            }
            userMap.get(user).add(banId);
        }
        
        // 계산하기
        for(int i=0; i<id_list.length; i++){
            String user = id_list[i];
            int count = 0;
            if(!userMap.containsKey(user)){
                answer[i] = count;
                continue;
            }
            
            for(String banId : userMap.get(user)){
                if(banMap.get(banId)>=k){
                    count++;
                }
            }
            
            answer[i] = count;

        }
        
        
        
        return answer;
    }
}
```
여기서 생각을 <신고한 사람, 신고 당한 사람 리스트>로 하고

<신고 당한 사람, 횟수> 이렇게 한번에 하면 나중에 if문이 추가된다. 

이유는 중복이 이러나기 때문이다. 이를 해결하기 위해 또 set이 필요하니 나눠서 해야한다.

생각을 바꿔서 
<신고된 사람, 신고한 사람 리스트>를 계산하면 간결하다. 

그 이후에 <신고한사람, 메일 받는 횟수>를 계산하면 편하다.


# 트리
데이터를 저장하고 탐색하기에 유용한 구조

## 배열로 표현하기
- 루트 노드는 배열 인덱스 1번에 저장
- 왼쪽 자식 노드의 배열 인덱스는 부모 노드의 배열 인덱스 * 2
- 오른쪽 자식 노드의 배열 인덱스는 부모 노드의 배열 인덱스 * 2 + 1

# 문제 25 트리 순회
## 코드
```java
private static String preorder(int[] nodes, int idx){
    // 루트, 왼쪽, 오른쪽
    return nodes[idx] + " " +
            preorder(nodes, 2*idx +1) +
            preorder(nodes, 2*idx +2);
}

```

# 문제 26 예상 대진표
https://school.programmers.co.kr/learn/courses/30/lessons/12985
## 문제 분석
숫자가 /2의 올림이 같으면 같은 대진표가 된다.

## 책의 내용
 트리로 생각하면 1라운드에 1~8

2 라운드는 각 1,2 - 3,4 가 대진해서 위로 올라가는 원리

4, 7은 무조건 이긴다는 가정이고 나머지는 뭐가 이기는 상관 없다.

노드 번호로 생각한다.
## 코드
```java
class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        while(a!=b){
            answer++;
            a = (a+1)/2;
            b = (b+1)/2;
        }
        return answer;
    }
}
```

# 문제 27 다단계 칫솔 판매
https://school.programmers.co.kr/learn/courses/30/lessons/77486

## 코드

```java
import java.util.*;
class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> nodeMap = new HashMap();
        Map<String, Integer> amountMap = new HashMap();
        
        for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String perent = referral[i];
            nodeMap.put(child, perent);
        }
        
        for(int i=0; i<seller.length; i++){
            int revenue = amount[i]*100;
            
            for(String child = seller[i]; !child.equals("-") && revenue>0; 
                child=nodeMap.get(child))
            {
                int pay = revenue/10;
                amountMap.put(child, amountMap.getOrDefault(child, 0)+revenue-pay);
                revenue = pay;
            }
        }
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            answer[i] = amountMap.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
}
```