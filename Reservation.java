import java.sql.Date;

public class Reservation {
    private int id;
    private int customerId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation(int id, int customerId, int roomNumber, Date checkIn, Date checkOut) {
        this.id = id;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public int getRoomNumber() { return roomNumber; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
}
