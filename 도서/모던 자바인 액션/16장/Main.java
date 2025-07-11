import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop("Best");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned aftre " + invocationTime);

        // 다른 작업

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);

        } catch (Exception e){
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Retrieval returned aftre " + retrievalTime);
    }

}
