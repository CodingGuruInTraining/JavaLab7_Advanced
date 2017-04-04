package com.mark;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * This class manages the program as well as other class objects.
 */
public class TicketManager {
    // Creates a file manager object to retrieve any previously saved tickets.
    private static TicketFileManager filer = new TicketFileManager();
    // Retrieves any saved tickets.
    private static LinkedList<Ticket> ticketQueue = filer.fileReader(filer.openFile);
    // Creates empty list for any resolved tickets in the future.
    private static LinkedList<Ticket> resolvedTickets = new LinkedList<>();

    private void mainMenu() {
        // Continues looping through menu options until the exit option is chosen.
        while (true) {
            // Displays a menu and retrieves the User's input.
            System.out.println("1) Enter New Ticket\n2) Delete Ticket by ID\n3) Delete " +
                    "Ticket by Issue\n4) Search by Issue\n5) Display Open Tickets\n6) Quit");
            int task = Input.getPositiveIntInput("Enter your selection from list:");

            // Uses the User's input to decide what action to take.
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
                    exitEvent();
                    return;
                default:
                    break;
            }
        }
    }

    protected static void addTicket() {
        // Defines initial variables.
        boolean moreProblems = true;
        String description;
        String reporter;
        Date dateReported = new Date();
        int priority;

        // Exception handler.
        try {
            // Loops until the User no longer wants to continue.
            while (moreProblems) {
                // Retrieves User's inputs.
                description = Input.getStringInput("Enter problem:");
                reporter = Input.getStringInput("Who reported this issue?:");
                // Retrieves the User's input until it fits within a set range.
                do {
                    priority = Input.getPositiveIntInput("Enter priority of this issue (1 minor - 5 urgent):");
                }
                while (priority > 5 || priority < 1);

                // Creates new ticket object and adds to list object.
                Ticket t = new Ticket(description, priority, reporter, dateReported);
                addTicketByPriority(t);

                // Prompts User whether to continue.
                String more = Input.getStringInput("More tickets? (Y/N)");
                if (more.equalsIgnoreCase("N")) {
                    // Signals loop to stop.
                    moreProblems = false;
                }
            }
        }
        // Catch for entering wrong data type.
        catch (NumberFormatException err) {
            System.out.println("Please enter a number for the priority.");
        }
    }

    protected static void addTicketByPriority(Ticket newTicket) {
        // Checks if the ticket list is empty and adds provided ticket to it.
        if (ticketQueue.size() == 0) {
            ticketQueue.add(newTicket);
            return;
        }
        // Loops through list and sorts objects by their priority level.
        int newTicketPriority = newTicket.getUrgency();
        for (int x = 0; x < ticketQueue.size(); x++) {
            if (newTicketPriority >= ticketQueue.get(x).getUrgency()) {
                ticketQueue.add(x, newTicket);
                return;
            }
        }
        // Adds new ticket to end of list if it is not more urgent than any
        // open tickets.
        ticketQueue.addLast(newTicket);
    }

    protected void deleteByID() {
        // Displays all open tickets and checks if list is empty.
        printAllTickets(ticketQueue);
        if (ticketQueue.size() == 0) {
            System.out.println("There are no tickets to delete.");
            return;
        }

        // Sets loop indicator boolean.
        boolean foundID = false;
        // Exception handler.
        try {
            // Loops until the ID is found.
            while (foundID == false) {
                // Retrieves User input.
                int input = Input.getPositiveIntInput("Enter ID of Ticket to delete:");
                // Loops through all open tickets and compares their ID to the User's input.
                for (Ticket t : ticketQueue) {
                    if (t.getTicketID() == input) {
                        // Signals loop to stop and then performs deletion process.
                        foundID = true;
                        closeTicket(t);
                        System.out.println(String.format("Ticket %d has been deleted.", input));
                        break;
                    }
                }
                // Displays message if ID is not found.
                if (foundID == false) {
                    System.out.println(String.format("Ticket %d was not found. Try again.", input));
                }
            }
            // Displays updated list of open tickets.
            printAllTickets(ticketQueue);
        }
        catch (InputMismatchException err) {
            // Catch for entering a non-integer for ID.
            System.out.println("That is not a correct number format.");
        }
    }

    protected void deleteByIssue () {
        // Retrieves User's input.
        String searchString = Input.getStringInput("Enter description of Issue to delete:");
        // Runs method to search through open tickets for the provided string. A list of
        // partial matches is returned.
        LinkedList<Ticket> results = searchDescription(searchString);
        // Checks if more than one ticket was found.
        if (results.size() > 1) {
            System.out.println("Your search term is not specific enough to identify Ticket.");
        }
        else {
            // Runs deletion process.
            closeTicket(results.get(0));
            System.out.println("Ticket removed.");
        }
    }

    protected void searchByIssue() {
        // Retrieves User's input, searches through open tickets for partial matches,
        // and then displays the tickets found.
        String searchString = Input.getStringInput("Enter term to search descriptions for:");
        LinkedList<Ticket> results = searchDescription(searchString);
        printAllTickets(results);
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        // Loops through ticket list and displays each item's string value.
        System.out.println(" ------- All Tickets ------- ");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
        System.out.println(" ----- End of Tickets ------ ");
    }

    protected LinkedList<Ticket> searchDescription (String searchString) {
        // Searches the ticket list for partial matches of provided term.
        // These are added to a new list and returned.
        LinkedList<Ticket> resultsList = new LinkedList<>();
        for (Ticket t : ticketQueue) {
            if (t.getDescription().contains(searchString)) {
                resultsList.add(t);
            }
        }
        return resultsList;
    }

    protected void closeTicket(Ticket t) {
        // Prompts User to provide a resolution.
        String resolution = Input.getStringInput("How was the Ticket resolved?");
        Date closeDate = new Date();
        // Uses the ticket's setters to update certain attributes.
        t.setIsOpen(false);
        t.setClosedDate(closeDate);
        t.setResolution(resolution);
        // Adds provided ticket to resolved list and removes from open list.
        resolvedTickets.add(t);
        ticketQueue.remove(t);
    }

    protected void exitEvent() {
        // Uses the file manager object to save open tickets to a text file.
        filer.fileWriter(ticketQueue, filer.openFile);
        // Uses the file manager object to save closed tickets to a text file
        // that contains the current date.
        String currentDate = new SimpleDateFormat("MMMM_dd_yyyy").format(new Date());
        filer.fileWriter(resolvedTickets, filer.closeFile + currentDate + ".txt");
    }

    public static void main(String[] args) {
        // Creates manager object and runs its menu method.
        TicketManager manager = new TicketManager();
        manager.mainMenu();
    }
}
