/**
 * Represents a Conference reservation
 *
 * Contains all the details needed for a Conference reservation
 *
 * Extends from the abstract Reservation class
 */
public class ConferenceReservation extends Reservation {
    private int numberOfGuests;

    /**
     * Constructs a ConferenceReservation instance
     *
     * Calls the inherited constructor from the Reservation class
     *
     * @param numberOfNights the number of nights reserved
     * @param costOfReservation total cost of the reservation
     * @param numberOfGuests the number of guests
     */
    public ConferenceReservation(int numberOfNights, double costOfReservation, int numberOfGuests) {
        super(numberOfNights, costOfReservation);
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Returns the human-readable reservation type string
     *
     * @return reservation type string
     */
    @Override
    public String getReservationType() {
        return "Conference";
    }

    /**
     * String representation for the number of guests for conference reservations
     *
     * @return a string containing the number of guests
     */
    @Override
    public String getAdditionalDetails() {
        return "With no. of guests: " + numberOfGuests;
    }

    /**
     * Converts the ConferenceReservation object to a human-readable string
     *
     * @return String representation of the reservation
     */
    @Override
    public String toString() {
        return "ConferenceReservation, " + getReservationId() + ", " + getNumberOfNights() + ", " + getCostOfReservation() + ", " + numberOfGuests;
    }
}
