package com.mark;

import java.util.Date;

/**
 * This class defines a Ticket object and its attributes.
 */
public class Ticket {
    // Defines attributes.
    private String description;
    private String reportedBy;
    private Date openedDate;
    private int urgency;        // 1 = minor --- 5 = server on fire
    private boolean isOpen;
    private Date closedDate;
    private String resolution;
    protected int ticketID;

    // Creates a class-specific global counter.
    private static int staticTicketIDCounter = 70001;

    // Constructor.
    public Ticket(String desc, int p, String rep, Date repDate) {
        this.description = desc;
        this.urgency = p;
        this.reportedBy = rep;
        this.openedDate = repDate;
        this.ticketID = staticTicketIDCounter;
        // Increases global counter for next object.
        staticTicketIDCounter++;
        this.isOpen = true;
    }
    // Constructor for recreating saved objects.
    public Ticket(int id, String desc, int p, String rep, Date repDate, String res, Date close) {
        this.ticketID = id;
        this.description = desc;
        this.urgency = p;
        this.reportedBy = rep;
        this.openedDate = repDate;
        this.resolution = res;
        this.closedDate = close;
        // Makes sure the global counter isn't lower than Ticket ID.
        if (id >= staticTicketIDCounter) {
            staticTicketIDCounter = id + 1;
        }
    }

    // Getters.
    protected String getDescription() { return this.description; }
    protected int getUrgency() {
        return this.urgency;
    }
    protected int getTicketID() {
        return this.ticketID;
    }
    protected String getReportedBy() { return this.reportedBy; }
    protected Date getOpenedDate() { return this.openedDate; }
    protected String getResolution() { return this.resolution; }
    protected Date getClosedDate() { return this.closedDate; }

    // Setters.
    protected void setResolution(String res) { this.resolution = res; }
    protected void setClosedDate(Date closed) { this.closedDate = closed; }
    protected void setIsOpen(boolean status) { this.isOpen = status; }

    // Custom string output.
    @Override
    public String toString() {
        return "Ticket ID: " + this.ticketID +
                "\n\tIssue: " + this.description +
                " Priority: " + this.urgency +
                " Reported by: " + this.reportedBy +
                " Reported on: " + this.openedDate;
    }
}
