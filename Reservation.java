/**
 * Base abstract reservation class
 *
 * This class includes all the common properties of reservations
 */
public abstract class Reservation {
    private static int currentReservationId = 1;

    private int reservationId;
    private int numberOfNights;
    private double costOfReservation;

    /**
     * Constructor to be inherited by concrete subclasses
     *
     * Each call increments the static variable used to keep
     * track of the next reservation ID to be assigned
     *
     * @param numberOfNights the number of nights reserved
     * @param costOfReservation total cost of the reservation
     */
    protected Reservation(int numberOfNights, double costOfReservation) {
        this.reservationId = Reservation.currentReservationId;
        this.numberOfNights = numberOfNights;
        this.costOfReservation = costOfReservation;
        Reservation.currentReservationId++;
    }

    /**
     * Retrieves the reservation ID
     *
     * @return the reservation ID
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Retrieves the number of nights reserved
     *
     * @return the number of nights reserved
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * Retrieves the total cost of reservation
     *
     * @return the total cost of reservation
     */
    public double getCostOfReservation() {
        return costOfReservation;
    }

    /**
     * Abstract method used to return the human-readable name of the reservation type
     * In this case, either "Room" or "Conference"
     *
     * @return the string representation of the reservation type
     */
    public abstract String getReservationType();

    /**
     * Abstract method used to return the additional details based on reservation type
     * For "room", the guest requirements
     * For "conference", the number of guests
     *
     * @return a string representing the additional details of the reservation
     */
    public abstract String getAdditionalDetails();

    /**
     * Converts the Reservation object to a human-readable string
     *
     * @return String representation of the reservation
     */
    @Override
    public String toString() {
        return "Reservation, " + reservationId + ", " + numberOfNights + ", " + costOfReservation;
    }
}
