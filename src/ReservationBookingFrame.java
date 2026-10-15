
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReservationBookingFrame extends JFrame {

    private final Color NAVY = new Color(22, 34, 57);
    private final Color CORAL = new Color(239, 93, 78);
    private final Color BG = new Color(246, 248, 251);
    private final Color TEXT = new Color(39, 50, 68);
    private final Color MUTED = new Color(120, 130, 146);
    private final Color BORDER = new Color(225, 230, 237);

    private JTextField passengerName;
    private JTextField trainNumber;
    private JTextField trainName;
    private JTextField journeyDate;
    private JTextField source;
    private JTextField destination;
    private JComboBox<String> seatClass;

    // Main content container
    private JPanel main;
    private JLabel activeNavItem;
    public ReservationBookingFrame() {
        setTitle("Railway | Book Your Journey");
        setSize(1200, 800);
        setMinimumSize(new Dimension(900, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        root.add(createSidebar(), BorderLayout.WEST);

        main = new JPanel(new BorderLayout());
        main.setBackground(BG);
        main.add(createBookingContent(), BorderLayout.CENTER);

        root.add(main, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    // LEFT SIDEBAR
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(225, 0));
        sidebar.setBackground(NAVY);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(28, 18, 22, 18));

        JLabel logo = new JLabel("RAILWAY");
        logo.setFont(new Font("SansSerif", Font.BOLD, 25));
        logo.setForeground(Color.WHITE);

        JLabel tagline = new JLabel("YOUR JOURNEY, SIMPLIFIED");
        tagline.setFont(new Font("SansSerif", Font.PLAIN, 9));
        tagline.setForeground(new Color(170, 183, 203));

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(tagline);
        sidebar.add(Box.createVerticalStrut(45));
        sidebar.add(sectionLabel("MENU"));

        sidebar.add(navItem("Overview", false));
        sidebar.add(navItem("Book a Ticket", true));
        sidebar.add(navItem("My Bookings", false));
        sidebar.add(navItem("PNR Status", false));
        sidebar.add(navItem("Cancel Ticket", false));

        sidebar.add(Box.createVerticalGlue());

        JPanel help = new JPanel();
        help.setLayout(new BoxLayout(help, BoxLayout.Y_AXIS));
        help.setBackground(new Color(34, 48, 73));
        help.setBorder(new EmptyBorder(15, 13, 15, 13));
        help.setMaximumSize(new Dimension(200, 85));
        help.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel helpTitle = new JLabel("Need some help?");
        helpTitle.setForeground(Color.WHITE);
        helpTitle.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel helpText = new JLabel("We're here for you.");
        helpText.setForeground(new Color(190, 200, 215));
        helpText.setFont(new Font("SansSerif", Font.PLAIN, 11));

        help.add(helpTitle);
        help.add(Box.createVerticalStrut(7));
        help.add(helpText);

        sidebar.add(help);
        sidebar.add(Box.createVerticalStrut(20));

        JLabel profile = new JLabel("PT   Priyansh Tiwari");
        profile.setForeground(Color.WHITE);
        profile.setFont(new Font("SansSerif", Font.BOLD, 12));
        profile.setBorder(new EmptyBorder(0, 4, 0, 0));
        profile.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(profile);

        return sidebar;
    }

    private JLabel sectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 10));
        label.setForeground(new Color(140, 155, 179));
        label.setBorder(new EmptyBorder(0, 10, 14, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // SIDEBAR NAVIGATION
    private JLabel navItem(String text, boolean active) {
        JLabel label = new JLabel("   " + text);

        label.setFont(new Font("SansSerif",
                active ? Font.BOLD : Font.PLAIN, 13));

        label.setForeground(active ? Color.WHITE
                : new Color(188, 198, 214));

        label.setOpaque(true);
        label.setBackground(active ? CORAL : NAVY);
        label.setBorder(new EmptyBorder(13, 8, 13, 8));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (active) {
    activeNavItem = label;
}
        label.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        setActiveNav(label); // <-- YAHAN ADD KARNA HAI

        if ("Overview".equals(text)) {
            showContent(createDashboard());
        } else if ("Book a Ticket".equals(text)) {
            showContent(createBookingContent());
        } else if ("My Bookings".equals(text)) {
            new MyBookingsFrame().setVisible(true);
        } else if ("PNR Status".equals(text)
                || "Cancel Ticket".equals(text)) {
            new ReservationSearchFrame().setVisible(true);
        }
    }
});

        return label;
    }

    // SWITCH BETWEEN DASHBOARD AND BOOKING FORM
    private void showContent(JPanel panel) {
        main.removeAll();
        main.add(panel, BorderLayout.CENTER);
        main.revalidate();
        main.repaint();
    }

    // BOOKING FORM
    private JPanel createBookingContent() {
        JPanel bookingPanel = new JPanel(new BorderLayout(0, 22));
        bookingPanel.setBackground(BG);
        bookingPanel.setBorder(new EmptyBorder(28, 32, 28, 32));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));

        JLabel breadcrumb = new JLabel("RESERVATIONS  /  NEW BOOKING");
        breadcrumb.setForeground(MUTED);
        breadcrumb.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel heading = new JLabel("Where would you like to go?");
        heading.setForeground(TEXT);
        heading.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel subtitle = new JLabel(
                "Plan your next journey in a few simple steps.");
        subtitle.setForeground(MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        titles.add(breadcrumb);
        titles.add(Box.createVerticalStrut(10));
        titles.add(heading);
        titles.add(Box.createVerticalStrut(7));
        titles.add(subtitle);

        JLabel help = new JLabel("?  Help");
        help.setFont(new Font("SansSerif", Font.BOLD, 12));
        help.setForeground(TEXT);
        help.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(12, 15, 12, 15)));

        header.add(titles, BorderLayout.CENTER);
        header.add(help, BorderLayout.EAST);

        bookingPanel.add(header, BorderLayout.NORTH);
        bookingPanel.add(createFormArea(), BorderLayout.CENTER);

        return bookingPanel;
    }

    // DASHBOARD STATISTICS
    private JPanel createDashboard() {
        JPanel dashboard = new JPanel(new BorderLayout(0, 25));
        dashboard.setBackground(BG);
        dashboard.setBorder(new EmptyBorder(28, 32, 28, 32));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel breadcrumb = new JLabel("DASHBOARD  /  OVERVIEW");
        breadcrumb.setForeground(MUTED);
        breadcrumb.setFont(new Font("SansSerif", Font.BOLD, 10));

        JLabel heading = new JLabel("Dashboard Overview");
        heading.setForeground(TEXT);
        heading.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel subtitle = new JLabel(
                "Track your railway reservation activity.");
        subtitle.setForeground(MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        header.add(breadcrumb);
        header.add(Box.createVerticalStrut(10));
        header.add(heading);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);

        dashboard.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);

        JPanel cards = new JPanel(new GridLayout(2, 2, 16, 16));
        cards.setOpaque(false);

        try {
            int[] stats =
                    new ReservationSearchDAO().getBookingStatistics();

            cards.add(createStatCard(
                    "TOTAL BOOKINGS", String.valueOf(stats[0]),
                    "All reservations", new Color(51, 105, 220)));

            cards.add(createStatCard(
                    "CONFIRMED", String.valueOf(stats[1]),
                    "Successfully booked", new Color(25, 150, 100)));

            cards.add(createStatCard(
                    "CANCELLED", String.valueOf(stats[2]),
                    "Cancelled reservations", new Color(220, 85, 85)));

            cards.add(createStatCard(
                    "TODAY'S BOOKINGS", String.valueOf(stats[3]),
                    "Created today", new Color(145, 85, 200)));

        } catch (Exception ex) {
            JLabel error = new JLabel(
                    "<html>Unable to load statistics.<br>"
                    + "Please check your database connection.</html>");
            error.setForeground(Color.RED);
            cards.add(error);
            ex.printStackTrace();
        }

        content.add(cards, BorderLayout.NORTH);

        JButton refreshButton = new JButton("Refresh Statistics");
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        refreshButton.addActionListener(e ->
                showContent(createDashboard()));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setOpaque(false);
        bottom.add(refreshButton);

        content.add(bottom, BorderLayout.CENTER);
        dashboard.add(content, BorderLayout.CENTER);

        return dashboard;
    }

    private JPanel createStatCard(
            String title, String value, String description, Color accent) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(22, 22, 22, 22)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(MUTED);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 11));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(accent);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 32));

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setForeground(MUTED);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(valueLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(descriptionLabel);

        return card;
    }

    // BOOKING FORM AREA
    private JPanel createFormArea() {
        JPanel area = new JPanel(new BorderLayout(0, 18));
        area.setOpaque(false);

        JPanel steps = new JPanel(new GridLayout(1, 3, 12, 0));
        steps.setOpaque(false);
        steps.add(stepCard("01", "Journey", true));
        steps.add(stepCard("02", "Passenger", false));
        steps.add(stepCard("03", "Review", false));

        area.add(steps, BorderLayout.NORTH);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(24, 26, 24, 26)));

        JLabel title = new JLabel("Journey details");
        title.setFont(new Font("SansSerif", Font.BOLD, 19));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Enter your train and travel information.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(MUTED);

        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(22));

        source = new JTextField();
        destination = new JTextField();
        trainNumber = new JTextField();
        trainName = new JTextField();
        journeyDate = new JTextField(
                LocalDate.now().plusDays(1).toString());
        passengerName = new JTextField();

        seatClass = new JComboBox<>(new String[]{
                "Sleeper (SL)", "AC 3 Tier (3A)",
                "AC 2 Tier (2A)", "AC First Class (1A)"
        });
        styleCombo(seatClass);

        card.add(fieldRow(
                "FROM STATION", source, "e.g. Indore Junction",
                "TO STATION", destination, "e.g. Mumbai Central"));

        card.add(Box.createVerticalStrut(18));

        card.add(fieldRow(
                "TRAIN NUMBER", trainNumber, "e.g. 12951",
                "TRAIN NAME", trainName, "Enter train name"));

        card.add(Box.createVerticalStrut(18));

        card.add(fieldRow(
                "DATE OF JOURNEY", journeyDate, "YYYY-MM-DD",
                "TRAVEL CLASS", seatClass, null));

        card.add(Box.createVerticalStrut(24));
        card.add(separator());
        card.add(Box.createVerticalStrut(20));

        JLabel passengerTitle = new JLabel("Passenger information");
        passengerTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        passengerTitle.setForeground(TEXT);

        card.add(passengerTitle);
        card.add(Box.createVerticalStrut(6));

        JLabel passengerHint = new JLabel(
                "Enter the passenger's name as shown on their ID.");
        passengerHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        passengerHint.setForeground(MUTED);

        card.add(passengerHint);
        card.add(Box.createVerticalStrut(14));

        JPanel passengerRow = new JPanel(new GridLayout(1, 2, 16, 0));
        passengerRow.setOpaque(false);
        passengerRow.add(makeField(
                "PASSENGER FULL NAME", passengerName, "Enter full name"));

        JPanel emptyCell = new JPanel();
        emptyCell.setOpaque(false);
        passengerRow.add(emptyCell);

        passengerRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        passengerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
        card.add(passengerRow);

        card.add(Box.createVerticalStrut(24));

        JButton bookButton = new JButton("Continue to Booking  →");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookButton.setForeground(Color.WHITE);
        bookButton.setBackground(CORAL);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(new EmptyBorder(14, 20, 14, 20));
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        bookButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        bookButton.addActionListener(e -> validateForm());

        card.add(bookButton);
        card.add(Box.createVerticalStrut(12));

        JLabel secure = new JLabel(
                "Your information will be used for this reservation.");
        secure.setForeground(MUTED);
        secure.setFont(new Font("SansSerif", Font.PLAIN, 11));
        secure.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(secure);

        JScrollPane scroll = new JScrollPane(card);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().setBackground(BG);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        area.add(scroll, BorderLayout.CENTER);

        return area;
    }

    private JPanel stepCard(String number, String text, boolean active) {
        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 13));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(
                active ? CORAL : BORDER, 1, true));

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        numberLabel.setForeground(active ? CORAL : MUTED);

        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setForeground(active ? TEXT : MUTED);

        panel.add(numberLabel);
        panel.add(titleLabel);

        return panel;
    }

    private JPanel fieldRow(
            String label1, JTextField field1, String hint1,
            String label2, JComponent field2, String hint2) {

        JPanel row = new JPanel(new GridLayout(1, 2, 18, 0));
        row.setOpaque(false);

        row.add(makeField(label1, field1, hint1));

        if (field2 instanceof JTextField) {
            row.add(makeField(label2, (JTextField) field2, hint2));
        } else {
            JPanel right = new JPanel();
            right.setOpaque(false);
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

            JLabel label = fieldLabel(label2);
            field2.setAlignmentX(Component.LEFT_ALIGNMENT);
            field2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
            field2.setPreferredSize(new Dimension(200, 43));

            right.add(label);
            right.add(Box.createVerticalStrut(7));
            right.add(field2);
            row.add(right);
        }

        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

        return row;
    }

    private JPanel makeField(
            String labelText, JTextField field, String hint) {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(fieldLabel(labelText));
        panel.add(Box.createVerticalStrut(7));

        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(CORAL);
        field.setToolTipText(hint);
        field.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(10, 12, 10, 12)));

        field.setPreferredSize(new Dimension(200, 43));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(field);

        return panel;
    }

    private JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 10));
        label.setForeground(MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setForeground(TEXT);
        combo.setBorder(new LineBorder(BORDER, 1, true));
    }

    private JSeparator separator() {
        JSeparator line = new JSeparator();
        line.setForeground(BORDER);
        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return line;
    }

    // VALIDATE FORM AND SAVE RESERVATION
    private void validateForm() {
        String name = passengerName.getText().trim();
        String number = trainNumber.getText().trim();
        String train = trainName.getText().trim();
        String date = journeyDate.getText().trim();
        String from = source.getText().trim();
        String to = destination.getText().trim();

        if (name.isEmpty() || number.isEmpty() || train.isEmpty()
                || date.isEmpty() || from.isEmpty() || to.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill in all the fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (from.equalsIgnoreCase(to)) {
            JOptionPane.showMessageDialog(this,
                    "Source and destination must be different.",
                    "Invalid Journey",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate selectedDate;

        try {
            selectedDate = LocalDate.parse(date);

            if (selectedDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this,
                        "Journey date cannot be in the past.",
                        "Invalid Date",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                    "Enter the date in YYYY-MM-DD format.",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        ReservationDAO dao = new ReservationDAO();
        String pnr = dao.generatePNR();

        String selectedClass = seatClass.getSelectedItem().toString();

        Reservation reservation = new Reservation(
                pnr,
                "admin",
                name,
                number,
                train,
                selectedDate,
                from,
                to,
                selectedClass
        );

        try {
            boolean saved = dao.saveReservation(reservation);

            if (saved) {
                JOptionPane.showMessageDialog(this,
                        "Reservation booked successfully!\n\n"
                        + "PNR: " + pnr + "\n"
                        + "Passenger: " + name + "\n"
                        + "Train: " + train + "\n"
                        + "Journey Date: " + selectedDate,
                        "Booking Confirmed",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Reservation could not be saved.",
                        "Booking Failed",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(this,
                    "Database error: " + ex.getMessage(),
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void setActiveNav(JLabel selected) {
    if (activeNavItem != null) {
        activeNavItem.setBackground(new Color(248, 250, 252));
        activeNavItem.setForeground(new Color(71, 85, 105));
    }

    selected.setOpaque(true);
    selected.setBackground(new Color(37, 99, 235));
    selected.setForeground(Color.WHITE);

    activeNavItem = selected;
}
}
