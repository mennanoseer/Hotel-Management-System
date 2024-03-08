import java.time.LocalDate; // Add this import statement

public class Reservation {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public boolean overlaps(LocalDate newCheckInDate, LocalDate newCheckOutDate) {
        return !newCheckOutDate.isBefore(checkInDate) && !newCheckInDate.isAfter(checkOutDate);
    }
}
