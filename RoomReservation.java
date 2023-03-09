import java.util.List;

/**
 * Represents a Room reservation
 *
 * Contains all the details needed for a room reservation
 *
 * Extends from the abstract Reservation class
 */
public class RoomReservation extends Reservation {
    private List<String> requirements;

    /**
     * Constructs a RoomReservation instance
     *
     * Calls the inherited constructor from the Reservation class
     *
     * @param numberOfNights the number of nights reserved
     * @param costOfReservation total cost of the reservation
     * @param requirements list of requirements specified by the guest
     */
    public RoomReservation(int numberOfNights, double costOfReservation, List<String> requirements) {
        super(numberOfNights, costOfReservation);
        this.requirements = requirements;
    }

    /**
     * Returns the human-readable reservation type string
     *
     * @return reservation type string
     */
    @Override
    public String getReservationType() {
        return "Room";
    }

    /**
     * String representation for the additional requirements for room reservations
     *
     * @return a string containing the guest requirements
     */
    @Override
    public String getAdditionalDetails() {
        return "With requirements: " + String.join(" ", requirements);
    }

    /**
     * Converts the RoomReservation object to a human-readable string
     *
     * @return String representation of the reservation
     */
    @Override
    public String toString() {
        return "RoomReservation, " + getReservationId() + ", " + getNumberOfNights() + ", " + getCostOfReservation() + ", " + requirements;
    }
}
