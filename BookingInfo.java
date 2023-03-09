/**
 * This class encapsulates the pricing details required to compute the reservation cost
 */
public class BookingInfo {
    private String unitType;
    private double nightPrice;
    private double rateDecrement;

    /**
     * Creates a new BookingInfo instance
     *
     * @param unitType reservation type, for this case either "room" or "conference"
     * @param rateDecrement Rate of decrement used to compute reservation cost
     * @param nightPrice nightly cost used to compute reservation cost
     */
    public BookingInfo(String unitType, double rateDecrement, double nightPrice) {
        this.unitType = unitType;
        this.nightPrice = nightPrice;
        this.rateDecrement = rateDecrement;
    }

    /**
     * Retrieves the unit type
     *
     * @return the unit type
     */
    public String getUnitType() {
        return unitType;
    }


    /**
     * Retrieves the nightly price
     *
     * @return the nightly price
     */
    public double getNightPrice() {
        return nightPrice;
    }

    /**
     * Retrieves the rate decrement
     *
     * @return the rate decrement
     */
    public double getRateDecrement() {
        return rateDecrement;
    }
}
