import java.util.Scanner;

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelManager hotelManager = new HotelManager();

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Display Data");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotelManager.addUser();
                    break;
                case 2:
                    hotelManager.displayData();
                    break;
                case 3:
                    hotelManager.updateUser();
                    break;
                case 4:
                    hotelManager.deleteUser();
                    break;
                case 5:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
