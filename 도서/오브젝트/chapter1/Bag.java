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
    private void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    private void minusAmount(Long fee) {
        this.amount -= fee;
    }
    public void plusAmount(Long fee){
        this.amount += fee;
    }

    public Long hold(Ticket ticket) {
        if(hasInvitation()) {
            setTicket(ticket);
            return 0L;
        }else{
            minusAmount(ticket.getFee());
            setTicket(ticket);
            return ticket.getFee();
        }
    }
}
