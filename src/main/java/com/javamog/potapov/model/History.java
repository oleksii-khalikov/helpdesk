package com.javamog.potapov.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private int id;

    @Column(name = "date")
    private Date historyDate;

    @Column(name = "action")
    private String action;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User historyUser;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket historyTicket;

    public int getId() {
        return id;
    }

    public Date getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Date date) {
        this.historyDate = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getHistoryUser() {
        return historyUser;
    }

    public void setHistoryUser(User historyUser) {
        this.historyUser = historyUser;
    }

    public Ticket getHistoryTicket() {
        return historyTicket;
    }

    public void setHistoryTicket(Ticket historyTicket) {
        this.historyTicket = historyTicket;
    }
}
