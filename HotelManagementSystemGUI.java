import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;

public class HotelManagementSystemGUI extends JFrame {
    private HotelManager hotelManager;
    private JTextArea displayArea;

    public HotelManagementSystemGUI() {
        this.hotelManager = new HotelManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addUserButton = createButton("Add User");
        JButton displayDataButton = createButton("Display Data");
        JButton updateUserButton = createButton("Update User");
        JButton deleteUserButton = createButton("Delete User");
        JButton exitButton = createButton("Exit");

        buttonPanel.add(addUserButton);
        buttonPanel.add(displayDataButton);
        buttonPanel.add(updateUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.WEST);

        displayArea = new JTextArea(15, 30);
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(text);
            }
        });
        return button;
    }

    private void handleButtonClick(String buttonName) {
        switch (buttonName) {
            case "Add User":
                handleAddUser();
                break;
            case "Display Data":
                handleDisplayData();
                break;
            case "Update User":
                handleUpdateUser();
                break;
            case "Delete User":
                handleDeleteUser();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    private void handleAddUser() {
        String[] userTypes = { "Employee", "Guest" };
        String userType = (String) JOptionPane.showInputDialog(this, "Select user type:", "Add User",
                JOptionPane.PLAIN_MESSAGE, null, userTypes, "Employee");

        if (userType != null) {
            if (userType.equals("Employee")) {
                handleAddEmployee();
            } else if (userType.equals("Guest")) {
                handleAddGuest();
            }
        }
    }

    private void handleAddEmployee() {
        String[] employeeTypes = { "Manager", "Receptionist" };
        String employeeType = (String) JOptionPane.showInputDialog(this, "Select employee type:", "Add Employee",
                JOptionPane.PLAIN_MESSAGE, null, employeeTypes, "Manager");

        if (employeeType != null) {
            String name = JOptionPane.showInputDialog(this, "Enter employee name:", "Add Employee",
                    JOptionPane.PLAIN_MESSAGE);
            String id = JOptionPane.showInputDialog(this, "Enter employee ID:", "Add Employee",
                    JOptionPane.PLAIN_MESSAGE);

            if (employeeType.equals("Manager")) {
                String department = JOptionPane.showInputDialog(this, "Enter manager department:", "Add Manager",
                        JOptionPane.PLAIN_MESSAGE);
                Manager manager = new Manager(name, id, department);
                hotelManager.addEmployee(manager);
                displayArea.append("Manager added successfully!\n");
            } else if (employeeType.equals("Receptionist")) {
                String shift = JOptionPane.showInputDialog(this, "Enter receptionist shift:", "Add Receptionist",
                        JOptionPane.PLAIN_MESSAGE);
                Receptionist receptionist = new Receptionist(name, id, shift);
                hotelManager.addEmployee(receptionist);
                displayArea.append("Receptionist added successfully!\n");
            }
        }
    }

    private void handleAddGuest() {
        String name = JOptionPane.showInputDialog(this, "Enter guest name:", "Add Guest", JOptionPane.PLAIN_MESSAGE);
        String guestId = JOptionPane.showInputDialog(this, "Enter guest ID:", "Add Guest", JOptionPane.PLAIN_MESSAGE);

        String checkInDateStr = JOptionPane.showInputDialog(this, "Enter check-in date (YYYY-MM-DD):", "Add Guest",
                JOptionPane.PLAIN_MESSAGE);
        LocalDate checkInDate = LocalDate.parse(checkInDateStr);

        String checkOutDateStr = JOptionPane.showInputDialog(this, "Enter check-out date (YYYY-MM-DD):", "Add Guest",
                JOptionPane.PLAIN_MESSAGE);
        LocalDate checkOutDate = LocalDate.parse(checkOutDateStr);

        displayAvailableRooms(checkInDate, checkOutDate);

        String roomId = JOptionPane.showInputDialog(this, "Enter room ID:", "Add Guest", JOptionPane.PLAIN_MESSAGE);

        Room selectedRoom = hotelManager.findRoomById(roomId);

        if (selectedRoom != null && selectedRoom.isRoomAvailable(checkInDate, checkOutDate)) {
            Guest guest = new Guest(name, guestId, checkInDate, checkOutDate, roomId);
            hotelManager.addGuest(guest);
            selectedRoom.addReservation(checkInDate, checkOutDate);

            displayArea.append("Guest added successfully!\n");
        } else {
            displayArea.append("The selected room is not available for the specified dates.\n");
        }
    }

    private void handleUpdateUser() {
        String userId = JOptionPane.showInputDialog(this, "Enter user ID to update:", "Update User",
                JOptionPane.PLAIN_MESSAGE);

        if (userId != null) {
            User user = hotelManager.findUserById(userId);

            if (user != null) {
                if (user instanceof Guest) {
                    handleUpdateGuest((Guest) user);
                }
            } else {
                displayArea.append("User with ID " + userId + " not found.\n");
            }
        }
    }

    private void handleUpdateGuest(Guest guest) {
        String newName = JOptionPane.showInputDialog(this, "Enter new name for guest:", "Update Guest",
                JOptionPane.PLAIN_MESSAGE);

        String newCheckInDateStr = JOptionPane.showInputDialog(this, "Enter new check-in date (YYYY-MM-DD):",
                "Update Guest", JOptionPane.PLAIN_MESSAGE);
        LocalDate newCheckInDate = LocalDate.parse(newCheckInDateStr);

        String newCheckOutDateStr = JOptionPane.showInputDialog(this, "Enter new check-out date (YYYY-MM-DD):",
                "Update Guest", JOptionPane.PLAIN_MESSAGE);
        LocalDate newCheckOutDate = LocalDate.parse(newCheckOutDateStr);

        displayAvailableRooms(newCheckInDate, newCheckOutDate);

        String newRoomId = JOptionPane.showInputDialog(this, "Enter new room ID:", "Update Guest",
                JOptionPane.PLAIN_MESSAGE);

        Room newRoom = hotelManager.findRoomById(newRoomId);

        if (newRoom != null && newRoom.isRoomAvailable(newCheckInDate, newCheckOutDate)) {
            guest.setName(newName);
            guest.setCheckInDate(newCheckInDate);
            guest.setCheckOutDate(newCheckOutDate);
            hotelManager.updateGuestReservation(guest, newRoom, newCheckInDate, newCheckOutDate);
            displayArea.append("Guest data updated successfully!\n");
        } else {
            displayArea.append("The selected room is not available for the specified dates.\n");
        }
    }

    private void handleDeleteUser() {

        String userId = JOptionPane.showInputDialog(this, "Enter user ID to delete:", "Delete User",
                JOptionPane.PLAIN_MESSAGE);

        if (userId != null) {
            User user = hotelManager.findUserById(userId);

            if (user != null) {
                if (user instanceof Guest) {
                    handleDeleteGuest((Guest) user);
                }
            } else {
                displayArea.append("User with ID " + userId + " not found.\n");
            }
        }
    }

    private void handleDeleteGuest(Guest guest) {
        hotelManager.deleteGuest(guest);
        displayArea.append("Guest deleted successfully!\n");
    }

    private void displayAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        StringBuilder availableRooms = new StringBuilder("Available Rooms:\n");

        for (Room room : hotelManager.getRooms()) {
            if (room.isRoomAvailable(checkInDate, checkOutDate)) {
                availableRooms.append("Room ID: ").append(room.getRoomId()).append("\n");
            }
        }

        displayArea.append(availableRooms.toString());
    }

    private void handleDisplayData() {
        displayArea.setText("");

        StringBuilder data = new StringBuilder("Employee Data:\n");
        for (Employee employee : hotelManager.getEmployees()) {
            data.append(employee.getName()).append(" (ID: ").append(employee.getId()).append(")\n");
        }

        data.append("\nGuest Data:\n");
        for (Guest guest : hotelManager.getGuests()) {
            data.append(guest.getName()).append(" (Guest ID: ").append(guest.getGuestId()).append(")\n");
        }

        displayArea.append(data.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelManagementSystemGUI();
            }
        });
    }
}
