
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MyBookingsFrame extends JFrame {

    private JTable bookingsTable;
    private DefaultTableModel tableModel;

    public MyBookingsFrame() {
        setTitle("My Bookings | Online Reservation System");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(20, 32, 55));
        header.setBorder(BorderFactory.createEmptyBorder(22, 28, 22, 28));

        JLabel title = new JLabel("My Bookings");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("View your reservation history");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(200, 210, 225));

        JPanel heading = new JPanel();
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.setOpaque(false);
        heading.add(title);
        heading.add(Box.createVerticalStrut(6));
        heading.add(subtitle);

        header.add(heading, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        String[] columns = {
            "PNR", "Passenger", "Train No.", "Train Name",
            "Journey Date", "From", "To", "Class", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookingsTable = new JTable(tableModel);
        bookingsTable.setRowHeight(38);
        bookingsTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bookingsTable.getTableHeader().setFont(
            new Font("Segoe UI", Font.BOLD, 12));
        bookingsTable.getTableHeader().setBackground(
            new Color(238, 242, 247));
        bookingsTable.setSelectionBackground(new Color(219, 234, 254));
        bookingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JLabel sectionTitle = new JLabel("Reservation History");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 18, 0));

        content.add(sectionTitle, BorderLayout.NORTH);
        content.add(scrollPane, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(e -> loadBookings());

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 24, 14));
        footer.setBackground(Color.WHITE);
        footer.add(refreshButton);
        add(footer, BorderLayout.SOUTH);

        loadBookings();
    }

    private void loadBookings() {
        tableModel.setRowCount(0);

        try {
            ReservationSearchDAO dao = new ReservationSearchDAO();
            List<Reservation> reservations = dao.findAllReservations();

            for (Reservation r : reservations) {
                tableModel.addRow(new Object[] {
                    r.getPnr(),
                    r.getPassengerName(),
                    r.getTrainNumber(),
                    r.getTrainName(),
                    r.getJourneyDate(),
                    r.getSourceStation(),
                    r.getDestinationStation(),
                    r.getSeatClass(),
                    r.getBookingStatus()
                });
            }

            if (reservations.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "No bookings found.", "My Bookings",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Could not load bookings.\n" + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
