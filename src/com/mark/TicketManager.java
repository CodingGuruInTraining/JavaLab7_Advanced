package com.mark;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * Created by hl4350hb on 3/20/2017.
 */
public class TicketManager {

    private static LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

    private void mainMenu() {
        while (true) {
            System.out.println("1) Enter New Ticket\n2) Delete Ticket by ID\n3) Delete " +
                    "Ticket by Issue\n4) Search by Issue\n5) Display Open Tickets\n6) Quit");
            int task = Input.getPositiveIntInput("Enter your selection from list:");

            switch (task) {
                case 1:
                    addTicket();
                    break;
                case 2:
                    deleteByID();
                    break;
                case 3:
                    deleteByIssue();
                    break;
                case 4:
                    searchByIssue();
                    break;
                case 5:
                    printAllTickets(ticketQueue);
                    break;
                case 6:
                    System.out.println("Quitting program...goodbye");
                    return;
                default:
                    break;
            }
        }
    }


    protected static void addTicket() {
        boolean moreProblems = true;
        String description;
        String reporter;
        Date dateReported = new Date();
        int priority;

        try {
            while (moreProblems) {
                description = Input.getStringInput("Enter problem:");

                reporter = Input.getStringInput("Who reported this issue?:");

                priority = Input.getPositiveIntInput("Enter priority of this issue (1 minor - 5 urgent:");

                Ticket t = new Ticket(description, priority, reporter, dateReported);
                addTicketByPriority(t);



                // FOR TESTING:
                // TODO remove once complete
                printAllTickets(ticketQueue);



                String more = Input.getStringInput("More tickets? (Y/N)");
                if (more.equalsIgnoreCase("N")) {
                    moreProblems = false;
                }
            }
        }
        catch (NumberFormatException err) {
            System.out.println("Please enter a number for the priority.");
        }
    }

    protected static void addTicketByPriority(Ticket newTicket) {
        if (ticketQueue.size() == 0) {
            ticketQueue.add(newTicket);
            return;
        }
        int newTicketPriority = newTicket.getUrgency();
        for (int x = 0; x < ticketQueue.size(); x++) {
            if (newTicketPriority >= ticketQueue.get(x).getUrgency()) {
                ticketQueue.add(x, newTicket);
                return;
            }
        }
        ticketQueue.addLast(newTicket);
    }

    protected static void deleteByID() {
        printAllTickets(ticketQueue);
        if (ticketQueue.size() == 0) {
            System.out.println("There are no tickets to delete.");
            return;
        }

        boolean foundID = false;
        try {
            while (foundID == false) {
                int input = Input.getPositiveIntInput("Enter ID of Ticket to delete:");

                for (Ticket t : ticketQueue) {
                    if (t.getTicketID() == input) {
                        foundID = true;
                        ticketQueue.remove(t);
                        System.out.println(String.format("Ticket %d has been deleted.", input));
                        break;
                    }
                }
                if (foundID == false) {
                    System.out.println(String.format("Ticket %d was not found. Try again.", input));
                }
            }
            printAllTickets(ticketQueue);
        }
        catch (InputMismatchException err) {
            // Catch for entering a non-integer for ID.
            System.out.println("That is not a correct number format.");
        }
    }

    protected void deleteByIssue () {
        String searchString = Input.getStringInput("Enter description of Issue to delete:");
        LinkedList<Ticket> results = searchDescription(searchString);
        if (results.size() > 1) {
            System.out.println("Your search term is not specific enough to identify Ticket.");
        }
        else {
            ticketQueue.remove(results.get(0));
            System.out.println("Ticket removed.");
        }
    }

    protected void searchByIssue() {
        String searchString = Input.getStringInput("Enter term to search descriptions for:");
        LinkedList<Ticket> results = searchDescription(searchString);
        printAllTickets(results);
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All Tickets ------- ");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
        System.out.println(" ----- End of Tickets ------ ");
    }

    protected LinkedList<Ticket> searchDescription (String searchString) {
        LinkedList<Ticket> resultsList = new LinkedList<>();
        for (Ticket t : ticketQueue) {
            if (t.getDescription().contains(searchString)) {
                resultsList.add(t);
            }
        }
        return resultsList;
    }

    public static void main(String[] args) {
        TicketManager manager = new TicketManager();
        manager.mainMenu();
    }



    // close ticket method
        // remove from list; send to file manager to append to file

    // TODO add try/catches
    // TODO limit priority input range
}
