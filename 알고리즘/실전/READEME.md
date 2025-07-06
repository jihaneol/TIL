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
# 두번째 
## 1. StringBuilder

```java
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public void solution(String[] s) {

        StringBuilder sb = new StringBuilder();
        int[] arr = {1, 2, 3};
        
        sb.append("110");

        sb.insert(2, "29"); // 11290

        sb.delete(2, 4); //110

        sb.lastIndexOf("0"); // 2

        // 만약 indexOf에 없는 문자열이면 -1을 반환한다.
        // insert는 무조건 0 이상이어야한다.
    }
}
```

# 세번째

## Patten

Java의 Pattern 클래스는 정규 표현식(Regular Expression)을 컴파일하고 처리할 수 있게 해주는 java.util.regex 패키지의 클래스입니다.

문자열에서 특정 패턴을 찾거나, 검증하거나, 치환할 때 주로 사용됩니다.

## Pattern
| 메서드                                         | 설명                              |
| ------------------------------------------- | ------------------------------- |
| `compile(String regex)`                     | 정규식 문자열을 컴파일하여 Pattern 객체 생성    |
| `compile(String regex, int flags)`          | 플래그 포함 컴파일                      |
| `matcher(CharSequence input)`               | 해당 정규식으로 검사할 문자열에 대한 Matcher 생성 |
| `split(CharSequence input)`                 | 정규식을 기준으로 문자열 분할                |
| `matches(String regex, CharSequence input)` | 한 줄로 정규식과 문자열 일치 여부 확인 (static) |

## Matcher
| 메서드                              | 설명                        |
| -------------------------------- | ------------------------- |
| `find()`                         | 일치하는 다음 부분 찾기             |
| `matches()`                      | 전체 문자열이 정규식과 완전히 일치하는지 확인 |
| `lookingAt()`                    | 문자열의 처음부터 일치하는지 확인        |
| `group()`                        | 일치한 문자열 반환                |
| `replaceAll(String replacement)` | 정규식과 일치하는 모든 부분 치환        |
| `start()` / `end()`              | 매치된 부분의 시작/끝 인덱스 반환       |


## 코드
```java
String[] banned_id = {"fr*d*", "b*o"};
Pattern[] regex = new Pattern[banned_id.length];

for (int i = 0; i < banned_id.length; i++) {
    regex[i] = Pattern.compile(banned_id[i].replace("*", "[a-z0-9]"));  // 또는 "[a-z0-9]*"
}

String id = "frodo";
boolean[] used = new boolean[10];  // 예시
int j = 0;

if (!used[j] && regex[j].matcher(id).matches()) {
    System.out.println(id + "는 금지 아이디 패턴과 일치");
}

```

# 거리두기 확인하기
## 문제 분석
맨해튼 거리가 2인 P는 안되는데 처음에 힘들게 2인 거리를 다 보려고 했다.

생각을 달리하면 된다. 1인 4방향으로 나의 범위를 늘리면 해결하는 문제이다.

첫번째 시도
```java
import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        // 잘 지키면 1, 안 지키면 0
        Arrays.fill(answer, 1);
        int[][] dir = {{0,1}, {1,0}, {-1,0},{0,-1}};
        int[][] dia = {{1,-1},{1,1},{-1,-1},{-1,1}};

        for(int t=0; t<5; t++){
            String[] place = places[t];
            loop : for(int r=0; r<5; r++){
                for(int c=0; c<5; c++){
                    if('P' == place[r].charAt(c)){
                        // 4 방향
                        for(int i=0; i<4; i++){
                            int nx = r;
                            int ny = c;
                            for(int j=0; j<2; j++){
                                nx += dir[i][0];
                                ny += dir[i][1];
                                if(nx<0 || ny<0 || nx>=5 || ny>=5) break;
                                if('X' == place[nx].charAt(ny)){
                                    break;
                                }
                                if('P' == place[nx].charAt(ny)){
                                    answer[t] = 0;
                                    break loop;
                                }
                            }
                        }

                        // 대각선
                        for(int i=0; i<4; i++){
                            int nx = r + dia[i][0];
                            int ny = c + dia[i][1];

                            if(nx<0 || ny<0 || nx>=5 || ny>=5) continue;

                            if(place[nx].charAt(ny)=='P'){
                                if(
                                        place[nx-dia[i][0]].charAt(ny) != 'X'
                                                ||
                                                place[nx].charAt(ny-dia[i][1]) != 'X'){

                                    answer[t]=0;
                                    break loop;
                                }
                            }
                        }
                    }
                }
            }

        }

        return answer;
    }
}
```

깔끔한 풀이
```java
class Solution {

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public int[] solution(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};

        for (int t = 0; t < places.length; t++) {
            char[][] place = new char[5][5];
            for (int i = 0; i < 5; i++) {
                place[i] = places[t][i].toCharArray();
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i][j] == 'P') {
                        for (int k = 0; k < 4; k++) {
                            int x = i + dx[k];
                            int y = j + dy[k];

                            if (x < 0 || x > 4 || y < 0 || y > 4)
                                continue;

                            if (place[x][y] == 'P' || place[x][y] == 'p')
                                answer[t] = 0;

                            if (place[x][y] == 'O')
                                place[x][y] = 'p';
                        }
                    }
                }
            }

        }

        return answer;
    }
}
```