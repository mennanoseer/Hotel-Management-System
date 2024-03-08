import java.time.LocalDate;

public class Guest implements User {
    private String name;
    private String guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String roomId;

    public Guest(String name, String guestId, LocalDate checkInDate, LocalDate checkOutDate, String roomId) {
        this.name = name;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public void displayDetails() {
        System.out.println("Guest: " + name + " (Guest ID: " + guestId + ")");
    }
}
