import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String shopName;
    private static final Random random = new Random();

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        int i = random.nextInt(Discount.Code.values().length);
        Discount.Code code = Discount.Code.values()[i];

        return String.format("%s:%.2f:%s", shopName, price, code);

    }

    private double calculatePrice(String product) {
        deley();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);

    }

    public Future<Double> getPriceAsync(String product) {
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(() -> {
//            try{
//                double price = calculatePrice(product); // 비동기적으로 게산 수행
//                futurePrice.complete(price); // 계산이 완료되면 Future에 값을 설정한다.
//            }catch(Exception e){
//                futurePrice.completeExceptionally(e);
//            }
//        }).start();
//        return futurePrice; // 게산 결과가 완료되길 기다리지 않고 Future를 반환한다.
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public static void deley() {
        int deley = 500 + random.nextInt(2000);
        try {
            Thread.sleep(deley);
//            throw new InterruptedException("테스트");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return this.shopName;
    }
}
