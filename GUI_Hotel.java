import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUI_Hotel {
    private HotelManager hotelManager;

    private JFrame frame;
    private JTextField userIdTextField;
    private JTextArea displayTextArea;

    public GUI_Hotel() {
        hotelManager = new HotelManager();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Hotel Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel userIdLabel = new JLabel("User ID:");
        userIdTextField = new JTextField(10);
        JButton displayButton = new JButton("Display Data");
        displayButton.addActionListener(new DisplayButtonListener());

        inputPanel.add(userIdLabel);
        inputPanel.add(userIdTextField);
        inputPanel.add(displayButton);

        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    private class DisplayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = userIdTextField.getText();
            displayTextArea.setText(""); // Clear the text area

            for (Employee employee : hotelManager.getEmployees()) {
                if (employee.getId().equals(userId)) {
                    employee.displayDetails();
                    return;
                }
            }

            for (Guest guest : hotelManager.getGuests()) {
                if (guest.getGuestId().equals(userId)) {
                    guest.displayDetails();
                    return;
                }
            }

            displayTextArea.append("User with ID " + userId + " not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelManagementSystemGUI());
    }
}
