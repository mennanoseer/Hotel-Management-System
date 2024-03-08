import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelManager {
    private ArrayList<Employee> employees;
    private ArrayList<Guest> guests;
    private ArrayList<Room> rooms;
    private Scanner scanner;

    public HotelManager() {
        this.employees = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room("101"));
        rooms.add(new Room("102"));
        rooms.add(new Room("103"));
        rooms.add(new Room("104"));
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }
    
    public void addUser() {
        System.out.println("Select user type:");
        System.out.println("1. Employee");
        System.out.println("2. Guest");
        System.out.print("Enter choice: ");
        int userType = scanner.nextInt();
        scanner.nextLine(); 

        switch (userType) {
            case 1:
                addEmployee();
                break;
            case 2:
                addGuest();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        String id = scanner.nextLine();
        System.out.println("Select employee type:");
        System.out.println("1. Manager");
        System.out.println("2. Receptionist");
        System.out.print("Enter choice: ");
        int employeeType = scanner.nextInt();
        scanner.nextLine(); 

        switch (employeeType) {
            case 1:
                System.out.print("Enter manager department: ");
                String department = scanner.nextLine();
                Manager manager = new Manager(name, id, department);
                employees.add(manager);
                System.out.println("Manager added successfully!");
                break;
            case 2:
                System.out.print("Enter receptionist shift: ");
                String shift = scanner.nextLine();
                Receptionist receptionist = new Receptionist(name, id, shift);
                employees.add(receptionist);
                System.out.println("Receptionist added successfully!");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void addGuest() {
        System.out.print("Enter guest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter guest ID: ");
        String guestId = scanner.nextLine();

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkInDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

        displayAvailableRooms(checkInDate, checkOutDate);

        System.out.print("Enter room ID: ");
        String roomId = scanner.nextLine();

        // Create the guest without updating room availability
        Guest guest = new Guest(name, guestId, checkInDate, checkOutDate, roomId);
        guests.add(guest);

        System.out.println("Guest added successfully!");
    }

    public void updateUser() {
        System.out.print("Enter user ID to update: ");
        String userId = scanner.nextLine();
    
        // Check if the user is a guest
        for (Guest guest : guests) {
            if (guest.getGuestId().equals(userId)) {
                // Update guest data
                System.out.print("Enter new name for guest: ");
                String newName = scanner.nextLine();
                guest.setName(newName);

                System.out.print("Enter new check-in date (YYYY-MM-DD): ");
                LocalDate newCheckInDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Enter new check-out date (YYYY-MM-DD): ");
                LocalDate newCheckOutDate = LocalDate.parse(scanner.nextLine());
    
                // Find the room based on the guest's room ID
                Room selectedRoom = findRoomById(guest.getRoomId());
    
                // Check if the new dates are valid and the room is available
                if (selectedRoom != null && selectedRoom.isRoomAvailable(newCheckInDate, newCheckOutDate)) {
                    guest.setCheckInDate(newCheckInDate);
                    guest.setCheckOutDate(newCheckOutDate);
                    System.out.println("Guest data updated successfully!");
                } else {
                    System.out.println("The selected room is not available for the specified dates.");
                }
                return;
            }
        }
    
        System.out.println("User with ID " + userId + " not found.");
    }

    private void displayAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        System.out.println("Available Rooms:");

        for (Room room : rooms) {
            if (room.isRoomAvailable(checkInDate, checkOutDate)) {
                System.out.println("Room ID: " + room.getRoomId());
            }
        }
    }

    public void displayData() {
        System.out.println("Employee Data:");
        for (Employee employee : employees) {
            employee.displayDetails();
        }

        System.out.println("\nGuest Data:");
        for (Guest guest : guests) {
            guest.displayDetails();
        }
    }
    public String displayDataAsString() {
        StringBuilder result = new StringBuilder();
    
        result.append("Employee Data:\n");
        for (Employee employee : employees) {
            result.append(employee.getName()).append(" - ").append(employee.getId()).append("\n");
        }
    
        result.append("\nGuest Data:\n");
        for (Guest guest : guests) {
            result.append(guest.getName()).append(" - ").append(guest.getGuestId()).append("\n");
        }
    
        return result.toString();
    }

    public void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        String userId = scanner.nextLine();

        for (Guest guest : guests) {
            if (guest.getGuestId().equals(userId)) {
                cancelReservation(guest);
                guests.remove(guest);
                System.out.println("Guest deleted successfully!");
                return;
            }
        }

        System.out.println("User with ID " + userId + " not found.");
    }
    public void deleteGuest(Guest guest) {
        guests.remove(guest);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }


    private void cancelReservation(Guest guest) {
        Room room = findRoomById(guest.getRoomId());
        if (room != null) {
            room.cancelReservation(guest.getCheckInDate(), guest.getCheckOutDate());
        }
    }

    public Room findRoomById(String roomId) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

 public User findUserById(String userId) {
        for (Guest guest : guests) {
            if (guest.getGuestId().equals(userId)) {
                return guest;
            }
        }
        for (Employee employee : employees) {
            if (employee.getId().equals(userId)) {
                return employee;
            }
        }
        return null;
    }


public void updateGuestReservation(Guest guest, Room newRoom, LocalDate newCheckInDate, LocalDate newCheckOutDate) {
    Room oldRoom = findRoomById(guest.getRoomId());

    // Check if the new room is different from the old room
    if (!newRoom.getRoomId().equals(oldRoom.getRoomId())) {
        // Remove the reservation from the old room
        oldRoom.cancelReservation(guest.getCheckInDate(), guest.getCheckOutDate());

        // Check if the new room is available for the specified dates
        if (!newRoom.isRoomAvailable(newCheckInDate, newCheckOutDate)) {
            System.out.println("The selected room is not available for the specified dates.");
            return; // Do not proceed with the update if the room is not available
        }

        // Add the reservation to the new room
        newRoom.addReservation(newCheckInDate, newCheckOutDate);
        guest.setRoomId(newRoom.getRoomId());
    }

    // Update guest check-in and check-out dates
    guest.setCheckInDate(newCheckInDate);
    guest.setCheckOutDate(newCheckOutDate);

    System.out.println("Guest reservation updated successfully!");
}
}