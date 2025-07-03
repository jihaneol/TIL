import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceEx {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        work1();
        ses.schedule(ScheduledExecutorServiceEx::work2, 10, TimeUnit.SECONDS);
        ses.shutdown();
    }

    private static void work1() {
        System.out.println("Hello from work1");

    }
    private static void work2() {
        System.out.println("Hello from work2");
    }
}
