package chapter1;

public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(long amount){
        this(null, amount);
    }
    public Bag(Invitation invitation, long amount){
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation(){
        return invitation != null;
    }
    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public void minusAmount(Long fee) {
        this.amount -= fee;
    }
    public void plusAmount(Long fee){
        this.amount += fee;
    }
}
