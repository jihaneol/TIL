package chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<Ticket>();
    public TicketOffice(Long amount, Ticket ... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket() {
       return tickets.remove(0);
    }

    public void plusAmount(Long fee) {
        this.amount += fee;
    }
    public void minusAmount(Long fee) {
        this.amount -= fee;
    }
}
