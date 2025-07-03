package pub_sub;

import java.util.concurrent.Flow;

// 통신할 구독자를 받는다.
public interface Publisher<T>{
    void subscribe(Subscriber<? super T> subscriber);
}
