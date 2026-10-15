
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationSearchDAO {

    public Reservation searchByPNR(String pnr)
            throws SQLException {

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pnr);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getString("pnr"),
                            rs.getString("username"),
                            rs.getString("passenger_name"),
                            rs.getString("train_number"),
                            rs.getString("train_name"),
                            rs.getDate("journey_date").toLocalDate(),
                            rs.getString("source_station"),
                            rs.getString("destination_station"),
                            rs.getString("seat_class")
                    );

                    reservation.setBookingStatus(
                            rs.getString("booking_status"));

                    return reservation;
                }
            }
        }

        return null;
    }

    // Fetch all reservations, newest first
    public List<Reservation> findAllReservations()
            throws SQLException {

        List<Reservation> reservations = new ArrayList<>();

        String sql =
                "SELECT * FROM reservations ORDER BY created_at DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getString("pnr"),
                        rs.getString("username"),
                        rs.getString("passenger_name"),
                        rs.getString("train_number"),
                        rs.getString("train_name"),
                        rs.getDate("journey_date").toLocalDate(),
                        rs.getString("source_station"),
                        rs.getString("destination_station"),
                        rs.getString("seat_class")
                );

                reservation.setBookingStatus(
                        rs.getString("booking_status"));

                reservations.add(reservation);
            }
        }

        return reservations;
    }
    
public int[] getBookingStatistics() throws java.sql.SQLException {

    int[] stats = new int[4];

    String sql =
        "SELECT " +
        "COUNT(*), " +
        "SUM(CASE WHEN booking_status = 'CONFIRMED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN booking_status = 'CANCELLED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN DATE(created_at) = CURRENT_DATE THEN 1 ELSE 0 END) " +
        "FROM reservations";

    try (java.sql.Connection con = DatabaseConnection.getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            stats[0] = rs.getInt(1); // Total bookings
            stats[1] = rs.getInt(2); // Confirmed bookings
            stats[2] = rs.getInt(3); // Cancelled bookings
            stats[3] = rs.getInt(4); // Today's bookings
        }
    }

    return stats;
}

}
