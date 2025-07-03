package pub_sub;

public class Main {
    public static void main(String[] args) {

        // c3 = c2 + c1;
        ArithmeticCell c3 = new ArithmeticCell("c3");
        SimpleCell c2 = new SimpleCell("c2");
        SimpleCell c1 = new SimpleCell("c1");

        // c5 = c3 + c4; 도 가능하다.
        c1.subscribe(c3::setLeft); // 왼쪽
        c2.subscribe(c3::setRight); // 오른쪽

        c1.onNext(10);
        System.out.println("----");
        c2.onNext(20);
        System.out.println("----");
        c1.onNext(15);

    }
}
