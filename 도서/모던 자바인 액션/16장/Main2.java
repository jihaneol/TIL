import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.Arrays.asList;

public class Main2 {
    static List<Shop> shops = asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));
    private final static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
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

    // 원하는 제품 가격 검색
    public static List<String> findPrice(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .toList();

//        return shops.parallelStream()
//                .map(shop -> String.format("%s price is %.2f",
//                        shop.getName(), shop.getPrice(product)))
//                .toList();
//        List<CompletableFuture<String>> priceFutures = shops.stream()
//                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",
//                        shop.getName(), shop.getPrice(product)), executor))
//                .toList();
//
//        return priceFutures.stream()
//                .map(CompletableFuture::join) // 모든 비동기 동작이 끝나길 기다린다.
//                .toList();
    }
}
