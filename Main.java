import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int customerId = -1;

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Show Available Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1:
                        customerId = HotelReservationSystem.addCustomer();
                        break;
                    case 2:
                        HotelReservationSystem.showAvailableRooms();
                        break;
                    case 3:
                        if (customerId == -1) {
                            System.out.println("Please add customer first!");
                        } else {
                            HotelReservationSystem.bookRoom(customerId);
                        }
                        break;
                    case 4:
                        HotelReservationSystem.viewReservations();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
