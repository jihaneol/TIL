package chpater11;

abstract class BasicRatePolicy {

    public Money caluculateFee(Phone phone){
        Money result = Money.ZERO;

        for(Call call : phone.getCalls()){
            result.plus(calculateCallFee(call));
        }
        return result;
    }

    abstract protected Money calculateCallFee(Call call);
}
