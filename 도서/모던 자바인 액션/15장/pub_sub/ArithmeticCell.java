package pub_sub;

public class ArithmeticCell extends SimpleCell{
    private int left, right;

    public ArithmeticCell(String name){
        super(name);
    }

    public void setLeft(int left){
        this.left = left;
        onNext(left + this.right);
    }


    public void setRight(int right) {
       this.right = right;
       onNext(right+ this.left);
    }
}
