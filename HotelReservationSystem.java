import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {
    private static Scanner scanner = new Scanner(System.in);

    // Add Customer
    public static int addCustomer() throws SQLException {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        String sql = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Customer added successfully!");
                return rs.getInt(1);
            }
        }
        return -1;
    }

    // Show available rooms
    public static void showAvailableRooms() throws SQLException {
        String sql = "SELECT * FROM rooms WHERE available = TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nAvailable Rooms:");
            while (rs.next()) {
                System.out.println("Room " + rs.getInt("room_number") +
                        " | Type: " + rs.getString("type") +
                        " | Price: " + rs.getDouble("price"));
            }
        }
    }

    // Book room
    public static void bookRoom(int customerId) throws SQLException {
        showAvailableRooms();
        System.out.print("\nEnter room number to book: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOut = scanner.nextLine();

        String sql = "INSERT INTO reservations (customer_id, room_number, check_in, check_out) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, roomNumber);
            stmt.setDate(3, Date.valueOf(checkIn));
            stmt.setDate(4, Date.valueOf(checkOut));
            stmt.executeUpdate();

            // Update room availability
            String updateRoom = "UPDATE rooms SET available = FALSE WHERE room_number = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateRoom)) {
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();
            }

            System.out.println("Room booked successfully!");
        }
    }

    // View reservations
    public static void viewReservations() throws SQLException {
        String sql = "SELECT r.id, c.name, r.room_number, r.check_in, r.check_out " +
                     "FROM reservations r JOIN customers c ON r.customer_id = c.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nReservations:");
            while (rs.next()) {
                System.out.println("Reservation ID: " + rs.getInt("id") +
                        " | Customer: " + rs.getString("name") +
                        " | Room: " + rs.getInt("room_number") +
                        " | Check-in: " + rs.getDate("check_in") +
                        " | Check-out: " + rs.getDate("check_out"));
            }
        }
    }
}
