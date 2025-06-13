# 1주 알고리즘 문제 풀이

## 아는 것과 모르는 것을 명확하게

### 1. 기록해라
어디까지 생각해봤는지 기록해라

### 2. 시험 보듯 공부하라

### 3. 짧은 시간 공부해서는 절대 코테 통과를 못한다.

### 4. 나만의 언어로 요약하라
정말 이해했는지 확인하는 방법은 이해한 내용을 요약해보는 거다.

## 의사 코드로 설계하는 연습하기
1. 프로그래밍 언어로 작성x
2. 일반인도 이해할 수 있는 자연어로 작성
3. 일정한 형식이 없다.

### 작성하는 방법
### 1. 세부 구현이 아닌 동작 중심으로 작성
국어 영어 수학 점수를 입력받는다.

실제 프로그래밍  요소는 추가하면 안된다.

### 2. 문제 해결 순서로 작성하라
1. 영어 성적 입력
2. 영어 성적이 60점을 넘는지 확인
   3. 60점 이상이면 통과
   4. 60점 미만이면 실패

### 3. 충분히 테스트 하라

# 1주차 배열
## 배열 이란
배열은 인덱스와 값을 일대일 대응해 관리하는 자료구조다.

어떤 위치에 있는 데이터든 한 번에 접근이 가능하다. 이런 접근 방식을 임의 접근이라고 한다.

## 배열과 차원
배열은 차원과는 무관하게 메모리에 연속 할당된다.

1차원 배열은 객체이므로, 2차원 배열은 1차원 배열 객체의 배열로 표현한다.

2차원 배열은 메모리에 데이터가 반드시 연속으로 저장되지 않을 수 있다.

## 문제 1 배열 정렬하기
Arrays.sort(arr) 의 sort메소드는 Tim-Sort로 구현되어있다.

## 문제 2 배열 제어하기
```java
Integer[] result = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new); //1
Arrays.sort(result, Collections.reverseOrder());
Arrays.stream(result).mapToInt(Integer::intValue).toArray();
```

1. Arrays.stream()으로 stream 변환 -> stream 프리미티브 타입인 IntStream 데이터를 boxed()를 통해 Integer 변환
-> distinct()로 중복 제거 -> Integer[]로 반환
2. distinct()는 O(n)을 만족한다.

sort() -> nlogN 의 시간 복잡도

## 문제 3 두 개 뽑아서 더하기
권장 시간 30분

https://school.programmers.co.kr/learn/courses/30/lessons/68644

### 문제 분석 하기
1. 두개의 인덱스 선택
2. 선택한 값 더해서 배열에 저장
3. 중복 제거 및 정렬하기
### 시간 복잡도 분석 하기
n제곱의 시간 복잡도

### 코드
```java
public int[] solution(int[] numbers) {
    List<Integer> list = new ArrayList();
    int n = numbers.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            list.add(numbers[i] + numbers[j]);
        }
    }

    return list.stream().distinct().sorted().mapToInt(Integer::intValue).toArray();
}
```

set으로 할 수 있다.
## 문제 4 모의 고사
https://school.programmers.co.kr/learn/courses/30/lessons/42840
### 문제 분석하기
1. 수포자 index와 answers index 맞춰서 비교하기
2. 같으면 수포자의 count 증가
2-1. 다르면 넘어가
3. 가장 큰수찾아서 번호를 출력
### 시간 복잡도
n
### 코드
```java
private int[] supo1 = {1,2,3,4,5};
    private int[] supo2 = {2,1,2,3,2,4,2,5};
    private int[] supo3 = {3,3,1,1,2,2,4,4,5,5};
    
    public int[] solution(int[] answers) {
        int[] res = new int[3];
        
        for(int i=0; i<answers.length; i++){
            if(supo1[i%5] == answers[i]){
                res[0]++;
            }
            if(supo2[i%8] == answers[i]){
                res[1]++;
            }
            if(supo3[i%10] == answers[i]){
                res[2]++;
            }
        }
        
        int max = Arrays.stream(res).max().getAsInt();
    
        List<Integer> list = new ArrayList();
        
        for(int i=0; i<res.length; i++){
            if(res[i]==max){
                list.add(i+1);
            }
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
```
patterns로 2차원 배열 만들어서 해도 된다.

## 문제 5 행렬의 곱셈
https://school.programmers.co.kr/learn/courses/30/lessons/12949
### 문제 분석하기
1. arr1 x,m 만들기
2. arr2 y 만들기
3. 곱해서 더하기
### 시간 복잡도
n ** 3
### 코드
```java
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int x = arr1.length;
        int m = arr1[0].length;
        int y = arr2[0].length;
        
        int[][] answer = new int[x][y];
        
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                for(int z=0; z<m; z++){
                    answer[i][j] += arr1[i][z]*arr2[z][j];
                }
            }
        }
        
        return answer;
    }
```

## 문제 6 실패율
https://school.programmers.co.kr/learn/courses/30/lessons/42889
### 문제 분석하기
1. 스테이지별 도전자 수를 구함
int[] challenger = int[] failArr
1. 각 스테이지 실패율 구하기
Fail
2. 실패율 내림차순 같으면 스테이지 오름차순 스테이지만 출력

### 시간 복잡도 
정렬 nlogn + 각 도전자수  n + 실패율 계산 n + 스테이지 정답 구할 때 n

### 코드
```java
class Solution {
    public int[] solution(int N, int[] stages) {
   
        // 각 스테이지별 도전자 수 저장
        int[] failArr = new int[N+2];
        
        for(int stage : stages){
            failArr[stage]++;
        }
        
        List<Fail> fails= new ArrayList();
        
        double len = stages.length;
        
        // 실패율 계산
        for(int i=1; i<=N; i++){
            
            int num = failArr[i];
        
            if(num==0){
                fails.add(new Fail(i, 0.0));
            }
            else {
                fails.add(new Fail(i, num/len));
                len -= failArr[i]; 
            }
        }
       
        // 정렬
        Collections.sort(list);
        
        return list.stream().mapToInt(Fail :: getStage).toArray();
    }
}
class Fail implements Comparable<Fail>{
   private int stage;
   private double rate;

   Fail(int s, double r){
      stage = s;
      rate = r;
   }

   public int getStage(){
      return stage;
   }

   public int compareTo(Fail f){
      return Double.compare(f.rate, rate) == 0
              ? Integer.compare(stage, f.stage)
              : Double.compare(f.rate, rate);
   }
}
```

## 생각할거
다음 스테이지 이동한 사용자가 없으면 0/0이 된다.

이 부분을 생각하고 작성을 했어야 했다.

# 문제 7 방문 길이
https://school.programmers.co.kr/learn/courses/30/lessons/49994

## 분석하기
지나온 길을 어떻게 처리할 것 인가?

hashSet<String> 으로 지나온 경로를 넣어준다.

## 시간 복잡도
dirs 길이 n
## 코드
```java
import java.util.*;
class Solution {
    
    private int[][] location = {{-1,0},{0,1},{1,0},{0,-1}}; // 위, 오, 아, 왼
    
    public int solution(String dirs) {
        
        int x = 5, y=5;
        
        Set<String> pass = new HashSet();
        
        for(char dir : dirs.toCharArray()){
            
            int d = charToInt(dir);

            int nx = location[d][0] + x;
            int ny = location[d][1] + y;
            
            if(!isRange(nx, ny)) continue;
            
            pass.add(nx + " " + ny + " " + x + " "+ y);
            pass.add(x + " " + y + " " + nx + " "+ ny);
            
            x = nx;
            y = ny;
        }
        
        return pass.size()/2;
    }
    
    public int charToInt(char c){
        return switch(c){
                case 'U' -> 0;
                case 'R' -> 1;
                case 'D' -> 2;
                default -> 3;
        };
    }
    
    public boolean isRange(int x, int y){
        return x>=0 && y>=0 && x<11 && y<11;
    }
}
```