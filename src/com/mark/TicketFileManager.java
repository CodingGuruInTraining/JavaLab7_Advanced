package com.mark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class TicketFileManager {

    // for saving open and closed tickets to separate txt files


    public TicketFileManager() {

    }

    protected void fileReader() {

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
