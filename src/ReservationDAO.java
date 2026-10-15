import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class ReservationDAO {

    // Generate a unique-looking PNR
    public String generatePNR() {
        return "PNR" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10)
                .toUpperCase();
    }

    // Save reservation details in MySQL
    public boolean saveReservation(Reservation reservation)
            throws SQLException {

        String sql = "INSERT INTO reservations "
                + "(pnr, username, passenger_name, train_number, "
                + "train_name, journey_date, source_station, "
                + "destination_station, seat_class, booking_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reservation.getPnr());
            ps.setString(2, reservation.getUsername());
            ps.setString(3, reservation.getPassengerName());
            ps.setString(4, reservation.getTrainNumber());
            ps.setString(5, reservation.getTrainName());

            ps.setDate(6, java.sql.Date.valueOf(
                    reservation.getJourneyDate()));

            ps.setString(7, reservation.getSourceStation());
            ps.setString(8, reservation.getDestinationStation());
            ps.setString(9, reservation.getSeatClass());
            ps.setString(10, reservation.getBookingStatus());

            int rowsInserted = ps.executeUpdate();

            return rowsInserted == 1;
        }
    }
    
public boolean cancelReservation(String pnr) throws java.sql.SQLException {
    String sql = "UPDATE reservations SET booking_status = ? "
               + "WHERE pnr = ? AND booking_status = 'CONFIRMED'";
               

    try (java.sql.Connection con = DatabaseConnection.getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, "CANCELLED");
        ps.setString(2, pnr);

        return ps.executeUpdate() == 1;
    }
}
}
