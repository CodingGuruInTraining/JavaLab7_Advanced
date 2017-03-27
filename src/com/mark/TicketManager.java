package com.mark;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class TicketManager {

    LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

    private void mainMenu() {
        while (true) {
            System.out.println("1) Enter New Ticket\n2) Delete Ticket by ID\n3) Display Open Tickets\n4) Quit");
            int task = Input.getPositiveIntInput("Enter your selection from list:");

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
                    System.out.println("Quitting program...goodbye");
                    return;
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

        try {
            while (moreProblems) {
                System.out.println("Enter problem:");
                description = sc.nextLine();

                System.out.println("Who reported this issue?:");
                reporter = sc.nextLine();

                System.out.println("Enter priority of this issue (1 minor - 5 urgent:");
                priority = Integer.parseInt(sc.nextLine());

                Ticket t = new Ticket(description, priority, reporter, dateReported);
//            tickets.add(t);
                addTicketByPriority(tickets, t);


                // FOR TESTING:
                // TODO remove once complete
                printAllTickets(tickets);


                System.out.println("More tickets? (Y/N)");
                String more = sc.nextLine();
                if (more.equalsIgnoreCase("N")) {
                    moreProblems = false;
                }
            }
        }
        catch (NumberFormatException err) {
            System.out.println("Please enter an integer for the priority.");
        }
    }

    protected static void addTicketByPriority(LinkedList<Ticket> tickets, Ticket newTicket) {
        if (tickets.size() == 0) {
            tickets.add(newTicket);
            return;
        }
        int newTicketPriority = newTicket.getUrgency();
        for (int x = 0; x < tickets.size(); x++) {
            if (newTicketPriority >= tickets.get(x).getUrgency()) {
                tickets.add(x, newTicket);
                return;
            }
        }
        tickets.addLast(newTicket);
    }

    protected static void deleteTicket(LinkedList<Ticket> tickets) {
        printAllTickets(tickets);
        if (tickets.size() == 0) {
            System.out.println("There are no tickets to delete.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean foundID = false;
        while (foundID == false) {
            System.out.println("Enter ID of Ticket to delete:");
            int input = sc.nextInt();

            for (Ticket t : tickets) {
                if (t.getTicketID() == input) {
                    foundID = true;
                    tickets.remove(t);
                    System.out.println(String.format("Ticket %d has been deleted.", input));
                    break;
                }
            }
            if (foundID == false) {
                System.out.println(String.format("Ticket %d was not found. Try again.", input));
            }
        }
        printAllTickets(tickets);
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All Tickets ------- ");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
        System.out.println(" ----- End of Tickets ----- ");
    }


    public static void main(String[] args) {
        TicketManager manager = new TicketManager();
        manager.mainMenu();
    }


    // question asking method?




    // main method
        // creates file manager on startup

    // close ticket method
        // remove from list; send to file manager to append to file

    // TODO add try/catches
    // TODO limit priority input range
}
