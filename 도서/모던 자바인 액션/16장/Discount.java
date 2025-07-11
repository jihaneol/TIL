public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is" +
                Discount.apply(quote.getPrice(),
                        quote.getDiscount());
    }

    private static double apply(double price, Discount.Code code) {
        deley();
        return price * (100 - code.percentage) / 100;
    }

    private static void deley() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
