# 첫번째

# 1. 미로 탈출 명령어 - 다시
https://school.programmers.co.kr/learn/courses/30/lessons/150365

# 2. 택배 배달
https://school.programmers.co.kr/learn/courses/30/lessons/150369

## 문제 분석
끝에서 부터 총 배달, 수거 개수를 더 해가면서 확인해주면 된다.

answer가 long인데 int로 해서 애를 먹었다..

## 코드
```java
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
    
        int total = 0;
        int deltotal = 0;
        int picktotal = 0;
        long answer = 0;
        for(int i=n-1; i>=0; i--){
            int del = deliveries[i];
            int pick = pickups[i];
            
            // 배달 && 수거
            deltotal +=del;
            picktotal +=pick;
            
            while(total< deltotal || total< picktotal){
                answer+=(i+1);
                total+=cap;
            }
        }
        return answer*2;
    }
}
```

# 3. 개인정보 수집
https://school.programmers.co.kr/learn/courses/30/lessons/150370

## 코드 
```java
import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList();
       
        int origin_day_type = ymdToInt(today);
        
        Map<String, Integer> term = new HashMap();
        
        for(String t : terms){
            String a[] = t.split(" ");
            term.put(a[0], Integer.parseInt(a[1])*28);
        }

        for(int i=0; i<privacies.length; i++){
            String privacie = privacies[i];
            
            String info = privacie.substring(privacie.length()-1, 
                                             privacie.length());
            
            String period =  privacie.substring(0, privacie.length()-2);
            
            
            int comp_day_type = ymdToInt(period)+term.get(info);
            
            if(origin_day_type >= comp_day_type) {
                answer.add(i+1);
            }  
            
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    public int ymdToInt(String term){
        String[] ymd = term.split("\\.");
        int year = Integer.parseInt(ymd[0]);
        int month = Integer.parseInt(ymd[1]);
        int day = Integer.parseInt(ymd[2]);
        
        return year*28*12 + month*28 + day;
    }
}
```