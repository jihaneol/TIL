import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main4 {
    public static void main(String[] args) {
        List<CompletableFuture<String>> futures = IntStream.range(0, 100)
                .mapToObj(i -> retryableTask(i, 2)) // 재시도 최대 2회
                .toList();

        CompletableFuture[] futures1 = futures.stream().map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);


        CompletableFuture.allOf(futures1).orTimeout(100, TimeUnit.NANOSECONDS).join();

// 재시도 함수

    }
    public static CompletableFuture<String> retryableTask(int i, int retryCount) {
        return CompletableFuture.supplyAsync(() -> {
            if (i % 3 ==0 || i % 7 == 0) throw new RuntimeException(i + "번 실패");
            return "성공: " + i;
        }).handle((result, ex) -> {
            if (ex != null && retryCount > 0) {
                System.out.println(i + " 재시도 중...");
                return retryableTask(i, retryCount - 1).join();  // 동기 재귀 재시도
            } else if (ex != null) {
                System.out.println(i + " 최종 실패");
                return "실패한 결과";
            } else {
                return result;
            }
        });
    }
}


