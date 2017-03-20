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

        while (true) {
            System.out.println("1) Enter New Ticket\n2) Delete Ticket by ID\n3) Display Open Tickets\n4) Quit");
            int task = Integer.parseInt(sc.nextLine());

            switch (task) {
                case 1:
                    addTicket(ticketQueue);
                    break;
                case 2:
                    deleteTicket(ticketQueue);
                    break;
                case 3:
                    printAllTickets(ticketQueue);
                    break;
                case 4:
                    System.out.println("Quitting program");
                    break;
                default:
                    break;
            }
        }
    }


    protected static void addTicket(LinkedList<Ticket> tickets) {
        Scanner sc = new Scanner(System.in);
        boolean moreProblems = true;
        String description;
        String reporter;
        Date dateReported = new Date();
        int priority;

        while (moreProblems) {
            System.out.println("Enter problem:");
            description = sc.nextLine();

            System.out.println("Who reported this issue?:");
            reporter = sc.nextLine();

            System.out.println("Enter priority of this issue (1 minor - 5 urgent:");
            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            tickets.add(t);

            // FOR TESTING:
            printAllTickets(tickets);

            System.out.println("More tickets?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }
        sc.close();
    }

    protected static void deleteTicket(LinkedList<Ticket> tickets) {

    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All Tickets ------- ");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
        System.out.println(" ------- End of Tickets ------- ");
    }

    // question asking method?




    // main method
        // creates file manager on startup

    // add new ticket method

    // display open tickets method

    // close ticket method
        // remove from list; send to file manager to append to file

}
