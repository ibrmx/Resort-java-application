import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The main class to run for this assignment
 */
public class ReportTester {

    /**
     * Reads the bookingInfo file
     *
     * @return the list of booking info read from the file
     * @throws FileNotFoundException if the file is not found
     */
    private static List<BookingInfo> readBookingInfoFile() throws FileNotFoundException {
        File bookingInfoFile = new File("bookingInfo");
        List<BookingInfo> bookingInfoList = new ArrayList<>();

        try (Scanner bookingInfoFileScanner = new Scanner(bookingInfoFile)) {
            while (bookingInfoFileScanner.hasNextLine()) {
                String fileLine = bookingInfoFileScanner.nextLine();
                final String[] lineTokens = fileLine.split(":");

                bookingInfoList.add(
                        new BookingInfo(
                                lineTokens[0].strip(),
                                Double.parseDouble(lineTokens[1].strip()),
                                Double.parseDouble(lineTokens[2].strip())
                        )
                );
            }
        }

        if (bookingInfoList.size() > 2) {
            System.out.println("bookingInfo.txt has more than 2 entries!");
            System.exit(1);
        }

        return bookingInfoList;
    }

    /**
     * Shows the menu to console
     */
    private static void showMenu() {
        System.out.println();
        System.out.println("1 to make a reservation");
        System.out.println("2 to check a specific reservation");
        System.out.println("3 to cancel a reservation");
        System.out.println("4 to display all reservations");
        System.out.println("5 to exit");
    }

    /**
     * Prompts for a valid integer from console from the user.
     *
     * @param prompt The string to prompt the user
     * @param keyboardInput the scanner object to read user input
     * @return the integer input from the user
     */
    private static int promptForInteger(String prompt, Scanner keyboardInput) {
        while (true) {
            System.out.print(prompt);
            try {
                String rawInput = keyboardInput.nextLine();
                return Integer.parseInt(rawInput.strip());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    /**
     * Prompts for a valid reservation type from the user
     *
     * @param keyboardInput the scanner object to read user input
     * @return the reservation type, either "r" or "c"
     */
    private static String promptForReservationType(Scanner keyboardInput) {
        while (true) {
            System.out.print("Input the type of the reservation (R)oom or (C)onference: ");
            String choice = keyboardInput.nextLine().strip().toLowerCase();
            if (choice.equals("r") || choice.equals("c")) {
                return choice;
            }
            System.out.println("Invalid choice, please try again.");
        }
    }

    /**
     * Prompts the user for a list of room reservation requirements
     *
     * @param keyboardInput the scanner object to read user input
     * @return list of guest requirements for room reservations
     */
    private static List<String> promptForRoomRequirements(Scanner keyboardInput) {
        while (true) {
            System.out.print("Enter maximum 3 guest requirements separated by spaces: ");
            String requirements = keyboardInput.nextLine().strip();
            String[] requirementArray = requirements.split(" ");
            if (requirementArray.length > 3) {
                System.out.println("Cannot accommodate more than 3 requirements.");
            }
            return Arrays.asList(requirementArray);
        }
    }

    /**
     * Display the table header for reservation details
     */
    private static void displayTableHeader() {
        System.out.println("Reserv. ID  Reserv. Type  NumOfNights      Reserv. Cost");
        System.out.println("-------------------------------------------------------");
    }

    /**
     * Display the reservation in tabular form
     *
     * @param reservation the reservation to display
     */
    private static void displayTabulatedReservation(Reservation reservation) {
        System.out.printf(
                "%-12d%-14s%-17d%-12.2f%s%n",
                reservation.getReservationId(),
                reservation.getReservationType(),
                reservation.getNumberOfNights(),
                reservation.getCostOfReservation(),
                reservation.getAdditionalDetails()
        );
    }

    /**
     * Handles prompting the user for details needed to book reservations
     * After the reservation, displays the reservation details
     *
     * @param keyboardInput the scanner object to read user input
     * @param resort the resort instance
     * @param reservationType the reservation type
     */
    private static void handleReservationPrompts(Scanner keyboardInput, MusandamResort resort, String reservationType) {
        int numberOfNights = promptForInteger("Enter number of nights: ", keyboardInput);
        Reservation reservation;
        if ("r".equals(reservationType)) {
            List<String> requirements = promptForRoomRequirements(keyboardInput);
            reservation = resort.addRoomReservation(numberOfNights, requirements);
        } else {
            int numberOfGuests = promptForInteger("Enter number of guests: ", keyboardInput);
            reservation = resort.addConferenceReservation(numberOfNights, numberOfGuests);
        }
        displayTableHeader();
        displayTabulatedReservation(reservation);
    }

    /**
     * Handles prompts for displaying specific reservations
     *
     * @param keyboardInput the scanner object to read user input
     * @param resort the resort instance
     */
    private static void handleCheckSpecificReservation(Scanner keyboardInput, MusandamResort resort) {
        int reservationId = promptForInteger("Input ReservationID: ", keyboardInput);
        Reservation reservation = resort.getReservationById(reservationId);
        if (reservation != null) {
            displayTableHeader();
            displayTabulatedReservation(reservation);
        } else {
            System.out.println("There is no such reservation .................");
        }
    }

    /**
     * Handles prompts for cancelling reservations
     *
     * @param keyboardInput the scanner object to read user input
     * @param resort the resort instance
     */
    private static void handleCancelReservation(Scanner keyboardInput, MusandamResort resort) {
        int reservationId = promptForInteger("Input ReservationID: ", keyboardInput);
        Reservation deletedReservation = resort.cancelReservationById(reservationId);
        if (deletedReservation != null) {
            System.out.println("The following reservation will be removed");
            displayTableHeader();
            displayTabulatedReservation(deletedReservation);
        } else {
            System.out.println("There is no such reservation .................");
        }
    }

    /**
     * Displays all the reservations in tabular form
     *
     * @param resort the resort instance
     */
    private static void handleDisplayAllReservations(MusandamResort resort) {
        displayTableHeader();
        for (Reservation reservation : resort.getReservations()) {
            displayTabulatedReservation(reservation);
        }
    }

    /**
     * Main method
     *
     * @param args the program arguments
     */
    public static void main(String[] args) {
        System.out.println("**** Welcome to Musandam Resort ****");
        int menuChoice = 0;

        List<BookingInfo> bookingInfoList = null;

        try {
            bookingInfoList = readBookingInfoFile();
        } catch (FileNotFoundException e) {
            System.out.println("Missing bookingInfo.txt file!");
            System.exit(1);
        }

        MusandamResort resort = new MusandamResort(bookingInfoList);

        try (Scanner keyboardInput = new Scanner(System.in)) {
            while (menuChoice != 5) {
                showMenu();
                menuChoice = promptForInteger("Input your choice: ", keyboardInput);

                switch (menuChoice) {
                    case 1:
                        String reservationType = promptForReservationType(keyboardInput);
                        handleReservationPrompts(keyboardInput, resort, reservationType);
                        break;
                    case 2:
                        handleCheckSpecificReservation(keyboardInput, resort);
                        break;
                    case 3:
                        handleCancelReservation(keyboardInput, resort);
                        break;
                    case 4:
                        handleDisplayAllReservations(resort);
                        break;
                    case 5:
                        System.out.println("Thank you for using the reservation system.");
                        break;
                    default:
                        System.out.println("Invalid input, please try again.");
                }
            }
        }
    }
}
