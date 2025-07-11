import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.Arrays.asList;

public class Main3 {


    private final static List<Shop> shops = asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("Goods"));

    private final static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 10),
            (r) -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.println(findPrice("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration);
    }

    //    public static List<String> findPrice(String product) {
//        return shops.stream()
//                .map(shop -> shop.getPrice(product)) // shop에서 계산한 가격 String
//                .map(Quote::parse) // 상점에서 반환된 문자열을 Quote 객체로 변환
//                .map(Discount::applyDiscount) // Quote에 할인 적용
//                .toList();
//    }
    public static List<String> findPrice(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor)) // shop에서 계산한 가격 String
                .map(future -> future.thenApply(Quote::parse)) // 상점에서 반환된 문자열을 Quote 객체로 변환
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor))) // Quote에 할인 적용
                .toList();

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

}
