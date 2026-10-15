import javax.swing.*;

import javax.swing.border.*;

import java.awt.*;

import java.sql.SQLException;



public class ReservationSearchFrame extends JFrame {



    private JTextField pnrField;

    private JPanel resultPanel;



    private final Color BACKGROUND = new Color(245, 247, 251);

    private final Color PRIMARY = new Color(37, 99, 235);

    private final Color TEXT_DARK = new Color(30, 41, 59);

    private final Color TEXT_MUTED = new Color(100, 116, 139);

    private final Color BORDER = new Color(226, 232, 240);



    public ReservationSearchFrame() {

        setTitle("RailEase | Reservation Search");

        setSize(900, 720);

        setMinimumSize(new Dimension(650, 450));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        getContentPane().setBackground(BACKGROUND);

        setLayout(new BorderLayout());



        add(createHeader(), BorderLayout.NORTH);



        // Make the entire main content scrollable

        JScrollPane mainScroll = new JScrollPane(createMainContent());

        mainScroll.setBorder(null);

        mainScroll.setHorizontalScrollBarPolicy(

                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        mainScroll.setVerticalScrollBarPolicy(

                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainScroll.getVerticalScrollBar().setUnitIncrement(18);

        mainScroll.getViewport().setBackground(BACKGROUND);



        add(mainScroll, BorderLayout.CENTER);



        setVisible(true);

    }



    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER),

                BorderFactory.createEmptyBorder(14, 24, 14, 24)));



        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));

        brand.setOpaque(false);



        JLabel logo = new JLabel("R", SwingConstants.CENTER);

        logo.setFont(new Font("SansSerif", Font.BOLD, 22));

        logo.setForeground(Color.WHITE);

        logo.setOpaque(true);

        logo.setBackground(PRIMARY);

        logo.setPreferredSize(new Dimension(42, 42));



        JPanel brandText = new JPanel();

        brandText.setOpaque(false);

        brandText.setLayout(new BoxLayout(brandText, BoxLayout.Y_AXIS));



        JLabel name = new JLabel("RailEase");

        name.setFont(new Font("SansSerif", Font.BOLD, 20));

        name.setForeground(TEXT_DARK);



        JLabel subtitle = new JLabel("ONLINE RESERVATION SYSTEM");

        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 10));

        subtitle.setForeground(TEXT_MUTED);



        brandText.add(name);

        brandText.add(Box.createVerticalStrut(3));

        brandText.add(subtitle);



        brand.add(logo);

        brand.add(brandText);



        JLabel status = new JLabel("● RESERVATION PORTAL");

        status.setFont(new Font("SansSerif", Font.BOLD, 11));

        status.setForeground(new Color(22, 163, 74));



        header.add(brand, BorderLayout.WEST);

        header.add(status, BorderLayout.EAST);



        return header;

    }



    private JPanel createMainContent() {

       JPanel main = new JPanel(new BorderLayout());

        main.setBackground(BACKGROUND);

        main.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));



        JPanel content = new JPanel();

        content.setOpaque(false);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));



        // Let BoxLayout calculate the full preferred height so the outer scroll pane works.



        JLabel eyebrow = new JLabel("TRAVEL MANAGEMENT");

        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 11));

        eyebrow.setForeground(PRIMARY);

        eyebrow.setAlignmentX(Component.LEFT_ALIGNMENT);



        JLabel title = new JLabel("Your journey,");

        title.setFont(new Font("SansSerif", Font.BOLD, 34));

        title.setForeground(TEXT_DARK);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);



        JLabel title2 = new JLabel("at your fingertips.");

        title2.setFont(new Font("SansSerif", Font.BOLD, 34));

        title2.setForeground(TEXT_DARK);

        title2.setAlignmentX(Component.LEFT_ALIGNMENT);



        JLabel description = new JLabel(

                "Retrieve your reservation details using your unique PNR.");

        description.setFont(new Font("SansSerif", Font.PLAIN, 14));

        description.setForeground(TEXT_MUTED);

        description.setAlignmentX(Component.LEFT_ALIGNMENT);



        content.add(eyebrow);

        content.add(Box.createVerticalStrut(12));

        content.add(title);

        content.add(title2);

        content.add(Box.createVerticalStrut(12));

        content.add(description);

        content.add(Box.createVerticalStrut(25));

        content.add(createSearchCard());

        content.add(Box.createVerticalStrut(20));

        content.add(createResultCard());



      main.add(content, BorderLayout.NORTH);

        return main;

    }



    private JPanel createSearchCard() {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBorder(BorderFactory.createCompoundBorder(

                new LineBorder(BORDER, 1, true),

                BorderFactory.createEmptyBorder(22, 22, 22, 22)));

        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.setMaximumSize(new Dimension(650, 190));



        JLabel label = new JLabel("PNR NUMBER");

        label.setFont(new Font("SansSerif", Font.BOLD, 11));

        label.setForeground(TEXT_DARK);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);



        JLabel hint = new JLabel(

                "Enter the PNR provided at the time of booking.");

        hint.setFont(new Font("SansSerif", Font.PLAIN, 12));

        hint.setForeground(TEXT_MUTED);

        hint.setAlignmentX(Component.LEFT_ALIGNMENT);



        pnrField = new JTextField();

        pnrField.setFont(new Font("SansSerif", Font.PLAIN, 15));

        pnrField.setForeground(TEXT_DARK);

        pnrField.setCaretColor(PRIMARY);

        pnrField.setBorder(BorderFactory.createCompoundBorder(

                new LineBorder(BORDER, 1, true),

                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        pnrField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));

        pnrField.setAlignmentX(Component.LEFT_ALIGNMENT);



        JButton searchButton = new JButton("Find Reservation  →");

        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        searchButton.setForeground(Color.WHITE);

        searchButton.setBackground(PRIMARY);

        searchButton.setFocusPainted(false);

        searchButton.setCursor(

                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        searchButton.setBorder(

                BorderFactory.createEmptyBorder(12, 18, 12, 18));

        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        searchButton.addActionListener(e -> searchReservation());

        pnrField.addActionListener(e -> searchReservation());



        card.add(label);

        card.add(Box.createVerticalStrut(6));

        card.add(hint);

        card.add(Box.createVerticalStrut(12));

        card.add(pnrField);

        card.add(Box.createVerticalStrut(12));

        card.add(searchButton);



        return card;

    }



    private JPanel createResultCard() {

        JPanel card = new JPanel(new BorderLayout(0, 16));

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(

                new LineBorder(BORDER, 1, true),

                BorderFactory.createEmptyBorder(22, 22, 22, 22)));

        card.setAlignmentX(Component.LEFT_ALIGNMENT);



        JLabel heading = new JLabel("RESERVATION DETAILS");

        heading.setFont(new Font("SansSerif", Font.BOLD, 12));

        heading.setForeground(TEXT_DARK);



        resultPanel = new JPanel(new BorderLayout());

        resultPanel.setBackground(new Color(248, 250, 252));

        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        resultPanel.setPreferredSize(new Dimension(600, 380));



        JLabel placeholder = new JLabel(

                "<html><div style='text-align:center;'>"

                + "<b style='color:#334155;'>No reservation loaded</b><br><br>"

                + "<span style='color:#64748B;'>Enter your PNR above "

                + "to view your journey.</span></div></html>",

                SwingConstants.CENTER);



        resultPanel.add(placeholder, BorderLayout.CENTER);



        card.add(heading, BorderLayout.NORTH);

        card.add(resultPanel, BorderLayout.CENTER);



        return card;

    }



    private void searchReservation() {

        String pnr = pnrField.getText().trim();



        if (pnr.isEmpty()) {

            JOptionPane.showMessageDialog(this,

                    "Please enter your PNR number.",

                    "PNR Required",

                    JOptionPane.WARNING_MESSAGE);

            pnrField.requestFocusInWindow();

            return;

        }



        try {

            ReservationSearchDAO dao = new ReservationSearchDAO();

            Reservation reservation = dao.searchByPNR(pnr);



            resultPanel.removeAll();



            if (reservation == null) {

                JLabel notFound = new JLabel(

                        "<html><div style='text-align:center;'>"

                        + "<b style='color:#DC2626;'>Reservation not found</b>"

                        + "<br><br><span style='color:#64748B;'>"

                        + "Check the PNR and try again.</span></div></html>",

                        SwingConstants.CENTER);



                resultPanel.add(notFound, BorderLayout.CENTER);



            } else {

                resultPanel.setLayout(new BorderLayout(0, 20));



                JPanel pnrBanner = new JPanel(new BorderLayout());

                pnrBanner.setOpaque(false);



                JLabel pnrLabel = new JLabel(

                        "PNR  " + reservation.getPnr());

                pnrLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

                pnrLabel.setForeground(PRIMARY);





String bookingStatus = reservation.getBookingStatus();



JLabel status = new JLabel("● " + bookingStatus);

status.setFont(new Font("SansSerif", Font.BOLD, 11));



if ("CANCELLED".equalsIgnoreCase(bookingStatus)) {

    status.setForeground(new Color(220, 38, 38));

} else {

    status.setForeground(new Color(22, 163, 74));

}



                pnrBanner.add(pnrLabel, BorderLayout.WEST);

                pnrBanner.add(status, BorderLayout.EAST);



                JPanel details = new JPanel(new GridLayout(0, 2, 18, 20));

                details.setOpaque(false);



                addDetail(details, "PASSENGER",

                        reservation.getPassengerName());

                addDetail(details, "TRAIN NUMBER",

                        reservation.getTrainNumber());

                addDetail(details, "TRAIN NAME",

                        reservation.getTrainName());

                addDetail(details, "JOURNEY DATE",

                        reservation.getJourneyDate().toString());

                addDetail(details, "FROM",

                        reservation.getSourceStation());

                addDetail(details, "TO",

                        reservation.getDestinationStation());

                addDetail(details, "TRAVEL CLASS",

                        reservation.getSeatClass());



                resultPanel.add(pnrBanner, BorderLayout.NORTH);

                resultPanel.add(details, BorderLayout.CENTER);

                JButton cancelButton = new JButton("Cancel Reservation");

cancelButton.setFont(new Font("SansSerif", Font.BOLD, 13));

cancelButton.setForeground(Color.WHITE);

cancelButton.setBackground(new Color(220, 38, 38));

cancelButton.setFocusPainted(false);

cancelButton.setCursor(

        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

cancelButton.setBorder(

        BorderFactory.createEmptyBorder(12, 18, 12, 18));



cancelButton.addActionListener(e -> {

    int choice = JOptionPane.showConfirmDialog(

            this,

            "Are you sure you want to cancel this reservation?",

            "Confirm Cancellation",

            JOptionPane.YES_NO_OPTION,

            JOptionPane.WARNING_MESSAGE);



    if (choice == JOptionPane.YES_OPTION) {

        try {

            ReservationDAO reservationDAO = new ReservationDAO();



           boolean cancelled =

        reservationDAO.cancelReservation(reservation.getPnr());



            if (cancelled) {

                JOptionPane.showMessageDialog(

                        this,

                        "Reservation cancelled successfully.",

                        "Cancellation Successful",

                        JOptionPane.INFORMATION_MESSAGE);



                searchReservation();

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "This reservation is already cancelled "

                                + "or could not be cancelled.",

                        "Cancellation Failed",

                        JOptionPane.WARNING_MESSAGE);



                searchReservation();

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(

                    this,

                    "Unable to cancel reservation.\n" + ex.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE);

        }

    }

});



JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

buttonPanel.setOpaque(false);

buttonPanel.add(cancelButton);



resultPanel.add(buttonPanel, BorderLayout.SOUTH);

            }



            resultPanel.revalidate();

            resultPanel.repaint();



        } catch (SQLException ex) {

            ex.printStackTrace();



            JOptionPane.showMessageDialog(this,

                    "Unable to retrieve reservation.\n" + ex.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE);

        }

    }



    private void addDetail(JPanel panel, String label, String value) {

        JPanel item = new JPanel();

        item.setOpaque(false);

        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));



        JLabel title = new JLabel(label);

        title.setFont(new Font("SansSerif", Font.BOLD, 10));

        title.setForeground(TEXT_MUTED);



        JLabel data = new JLabel(value == null ? "-" : value);

        data.setFont(new Font("SansSerif", Font.BOLD, 14));

        data.setForeground(TEXT_DARK);



        item.add(title);

        item.add(Box.createVerticalStrut(5));

        item.add(data);



        panel.add(item);

    }

}
