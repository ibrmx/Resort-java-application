import java.util.ArrayList;
import java.util.List;

/**
 * Represents a resort instance
 *
 * Contains the reservations and required details used to compute the reservation cost
 */
public class MusandamResort {
    private static double CONFERENCE_COST_PER_GUEST = 15.0;

    private List<Reservation> reservations;
    private List<BookingInfo> bookingInfoList;

    /**
     * Constructor for a MusandamResort instance
     *
     * @param bookingInfoList list containing the numerical details to compute the reservation costs
     */
    public MusandamResort(List<BookingInfo> bookingInfoList) {
        this.bookingInfoList = bookingInfoList;
        this.reservations = new ArrayList<>();
    }

    /**
     * Computes the base cost of the reservation
     * Does not include the cost of guest per night in conference reservations
     *
     * @param type the type of reservation to compute, either "room" or "conference"
     * @param numberOfNights the number of nights
     * @return the base cost of the reservation
     */
    private double computeBaseCostOfReservation(String type, int numberOfNights) {
        BookingInfo bookingInfoForType = null;
        for (BookingInfo bookingInfo : bookingInfoList) {
            if (type.equals(bookingInfo.getUnitType())) {
                bookingInfoForType = bookingInfo;
                break;
            }
        }
        // At this point bookingInfoForType should be non-null
        return (1.0 - bookingInfoForType.getRateDecrement()) * (bookingInfoForType.getNightPrice() * numberOfNights);
    }

    /**
     * Adds a RoomReservation to the list of reservations in the resort
     *
     * @param numberOfNights number of nights
     * @param requirements the list of guest requirements
     * @return the room reservation instance
     */
    public Reservation addRoomReservation(int numberOfNights, List<String> requirements) {
        double costOfReservation = computeBaseCostOfReservation("room", numberOfNights);
        Reservation roomReservation = new RoomReservation(numberOfNights, costOfReservation, requirements);
        this.reservations.add(roomReservation);
        return roomReservation;
    }

    /**
     * Adds a ConferenceReservation to the list of reservations in the resort
     *
     * @param numberOfNights number of nights
     * @param numberOfGuests number of guests
     * @return the conference reservation instance
     */
    public Reservation addConferenceReservation(int numberOfNights, int numberOfGuests) {
        double baseCostOfReservation = computeBaseCostOfReservation("conference", numberOfNights);
        double costOfReservation = baseCostOfReservation + (numberOfNights * numberOfGuests * CONFERENCE_COST_PER_GUEST);
        Reservation roomReservation = new ConferenceReservation(numberOfNights, costOfReservation, numberOfGuests);
        this.reservations.add(roomReservation);
        return roomReservation;
    }

    /**
     * Retrieves a specific reservation
     *
     * @param reservationId the reservationID to search
     * @return the retrieved reservation, or null if not found
     */
    public Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Cancel a specific reservation by the ID provided
     *
     * @param reservationId the reservationID to cancel
     * @return the cancelled reservation, or null if not found
     */
    public Reservation cancelReservationById(int reservationId) {
        int indexToRemove = -1;
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).getReservationId() == reservationId) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            return this.reservations.remove(indexToRemove);
        }
        return null;
    }

    /**
     * Retrieves the reservations for the resort
     *
     * @return the reservations for the resort
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
}
