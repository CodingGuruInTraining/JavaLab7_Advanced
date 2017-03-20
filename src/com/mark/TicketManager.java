package com.mark;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class TicketManager {
    public static void main(String[] args) {
        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
        Scanner sc = new Scanner(System.in);

        String description;
        String reporter;
        Date dateReported = new Date();     // may need to update when made?
        int priority;
        boolean moreProblems = true;

        while (moreProblems) {
            System.out.println("Enter problem:");
            description = sc.nextLine();

            System.out.println("Who reported this issue?:");
            reporter = sc.nextLine();

            System.out.println("Enter priority of this issue (1 minor - 5 urgent:");
            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            ticketQueue.add(t);

            printAllTickets(ticketQueue);

            System.out.println("More tickets?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }
        sc.close();
    }


    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All Tickets ------- ");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }

    // question asking method?




    // main method
        // creates file manager on startup

    // add new ticket method

    // display open tickets method

    // close ticket method
        // remove from list; send to file manager to append to file

}
