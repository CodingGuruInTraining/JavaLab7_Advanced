package com.mark;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * This class defines a file manager object that opens and closes
 * text files containing Ticket objects' data.
 */
public class TicketFileManager {
    // Defines attributes.
    protected LinkedList<Ticket> openTickets;
    protected LinkedList<Ticket> closedTickets;
    // Sets default filename strings.
    protected String openFile = "openTickets.txt";
    protected String closeFile = "Resolved_tickets_as_of_";

    // Getters.
    public LinkedList<Ticket> getOpenTickets() {
        return openTickets;
    }
    public LinkedList<Ticket> getClosedTickets() {
        return closedTickets;
    }

    // Constructor.
    public TicketFileManager() {
        // Retrieves open tickets when initialized.
        openTickets = fileReader(this.openFile);
    }

    protected LinkedList<Ticket> fileReader(String filename) {
        LinkedList<Ticket> tickets = new LinkedList<Ticket>();
        // Exception handler with buffered reader object created inside it.
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filename))) {
            // Reads first line of text file.
            String ticket_line = buffReader.readLine();
            // Creates a date formatter object.
            DateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
            // Loops until no more tickets (lines) exist in the text file.
            while (ticket_line != null) {
                // Splits the line into an array.
                String[] ticket_info = ticket_line.split(";");
                // Changes strings back to their original data type.
                int id = Integer.parseInt(ticket_info[0]);
                String desc = ticket_info[1];
                int p = Integer.parseInt(ticket_info[2]);
                String rep = ticket_info[3];
                Date repDate = formatter.parse(ticket_info[4]);
                // Sets last two variables as default.
                String res = null;
                Date close = null;
                // Checks if array item reads null (open ticket) or not null (closed
                // ticket).
                if (!ticket_info[5].equalsIgnoreCase("null")) {
                    // Sets variables to array values.
                    res = ticket_info[5];
                    close = formatter.parse(ticket_info[6]);
                }
                // Recreates ticket object and adds to list.
                Ticket t = new Ticket(id, desc, p, rep, repDate, res, close);
                tickets.add(t);
                // Reads next line to continue.
                ticket_line = buffReader.readLine();
            }
            // Closes reader and returns list.
            buffReader.close();
            return tickets;
        }
        // Catch for file reading error.
        catch (IOException err) {
            System.out.println("One or more files could not be found.");
        }
        // Catch for conversion error.
        catch (ParseException err) {
            System.out.println("An error exists with the saved dates.");
        }
        return tickets;
    }

    protected void fileWriter(LinkedList<Ticket> tickets, String filename) {
        // Exception handler with buffered writer object created in it.
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filename))) {
            // Loops through provided list and adds values to text file as a single
            // string with separators.
            for (Ticket t : tickets) {
                buffWriter.write(t.getTicketID() + ";" + t.getDescription() + ";" + t.getUrgency() +
                ";" + t.getReportedBy() + ";" + t.getOpenedDate() + ";" + t.getResolution() +
                ";" + t.getClosedDate() + "\n");
            }
            // Closes writer object once finished.
            buffWriter.close();
        }
        // Catch for file location error.
        catch (IOException err) {
            System.out.println("There was an issue saving the data.");
        }
    }
}

//helpful site:
//        http://stackoverflow.com/questions/4496359/how-to-parse-date-string-to-date