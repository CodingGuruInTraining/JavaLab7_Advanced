package com.mark;

import java.util.Date;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class Ticket {
    private String description;
    private String reportedBy;
    private Date openedDate;
    private int urgency;        // 1 = minor --- 5 = server on fire
    private boolean isOpen;
    private Date closedDate;
    private String resolution;

    private static int staticTicketIDCounter = 70001;
    protected int ticketID;

    public Ticket(String desc, int p, String rep, Date repDate) {
        this.description = desc;
        this.urgency = p;
        this.reportedBy = rep;
        this.openedDate = repDate;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    protected String getDescription() { return this.description; }
    protected int getUrgency() {
        return this.urgency;
    }
    protected int getTicketID() {
        return this.ticketID;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + this.ticketID +
                "\n\tIssue: " + this.description +
                " Priority: " + this.urgency +
                " Reported by: " + this.reportedBy +
                " Reported on: " + this.openedDate;
    }
}
