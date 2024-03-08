import java.time.LocalDate; // Add this import statement
import java.util.ArrayList;

public class Room {
    private String roomId;
    private ArrayList<Reservation> reservations;

    public Room(String roomId) {
        this.roomId = roomId;
        this.reservations = new ArrayList<>();
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isRoomAvailable(LocalDate checkInDate, LocalDate checkOutDate) {
        for (Reservation reservation : reservations) {
            if (reservation.overlaps(checkInDate, checkOutDate)) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(LocalDate checkInDate, LocalDate checkOutDate) {
        reservations.add(new Reservation(checkInDate, checkOutDate));
    }

    public void cancelReservation(LocalDate checkInDate, LocalDate checkOutDate) {
        reservations.removeIf(reservation -> reservation.overlaps(checkInDate, checkOutDate));
    }
}
