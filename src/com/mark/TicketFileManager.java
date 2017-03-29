package com.mark;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class TicketFileManager {

    protected LinkedList<Ticket> openTickets;
    protected LinkedList<Ticket> closedTickets;
    protected String openFile = "openTickets.txt";
    protected String closeFile = "closedTickets.txt";

    //TODO closed tickets file should have date
    //TODO add way to find file with wildcard

    // Getters.
    public LinkedList<Ticket> getOpenTickets() {
        return openTickets;
    }
    public LinkedList<Ticket> getClosedTickets() {
        return closedTickets;
    }

    // Constructor.
    public TicketFileManager() {
        openTickets = fileReader(this.openFile);
        closedTickets = fileReader(this.closeFile);
    }

    protected LinkedList<Ticket> fileReader(String filename) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filename))) {
            String ticket_line = buffReader.readLine();
            DateFormat formatter = new SimpleDateFormat();
            LinkedList<Ticket> tickets = new LinkedList<Ticket>();
            while (ticket_line != null) {
                String[] ticket_info = ticket_line.split(";");
                int id = Integer.parseInt(ticket_info[0]);
                String desc = ticket_info[1];
                int p = Integer.parseInt(ticket_info[2]);
                String rep = ticket_info[3];
                Date repDate = formatter.parse(ticket_info[4]);
                String res = ticket_info[5];
                Date close = formatter.parse(ticket_info[6]);
                Ticket t = new Ticket(id, desc, p, rep, repDate, res, close);
                tickets.add(t);
                ticket_line = buffReader.readLine();
            }
            buffReader.close();
            return tickets;
        }
        catch (IOException err) {
            System.out.println("One or more files could not be found.");
        }
        catch (ParseException err) {
            System.out.println("An error exists with the saved dates.");
        }
        return null;
    }

    protected void fileWriter(LinkedList<Ticket> tickets, String filename) {
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filename))) {
            for (Ticket t : tickets) {
                buffWriter.write(t.getTicketID() + ";" + t.getDescription() + ";" + t.getUrgency() +
                ";" + t.getReportedBy() + ";" + t.getOpenedDate() + ";" + t.getResolution() +
                ";" + t.getClosedDate() + "\n");
            }
            buffWriter.close();
        }
        catch (IOException err) {
            System.out.println("There was an issue saving the data.");
        }
    }
}
