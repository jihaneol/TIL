package pub_sub;

import java.util.ArrayList;
import java.util.List;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber> subscribers = new ArrayList<Subscriber>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue; // 구독한 셀에 새 값이 생겼을 때 값을 갱신해서 반응
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscribers();
    }

    // 모든 구독자한테 알림
    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

}
