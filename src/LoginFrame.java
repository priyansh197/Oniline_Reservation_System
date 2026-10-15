
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private final Color NAVY = new Color(20, 32, 58);
    private final Color BLUE = new Color(65, 95, 230);
    private final Color BACKGROUND = new Color(246, 248, 252);
    private final Color TEXT = new Color(35, 45, 65);
    private final Color MUTED = new Color(120, 130, 150);

    public LoginFrame() {

        setTitle("Online Reservation System");
        setSize(900, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(Color.WHITE);

        // LEFT SIDE
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(NAVY);
        leftPanel.setBorder(new EmptyBorder(45, 40, 40, 35));

        JLabel brand = new JLabel("RAILWAY RESERVATION");
        brand.setFont(new Font("SansSerif", Font.BOLD, 15));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel trainIcon = new JLabel("🚆");
        trainIcon.setFont(new Font("SansSerif", Font.PLAIN, 65));
        trainIcon.setForeground(Color.WHITE);
        trainIcon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcome = new JLabel(
            "<html>Your journey,<br>our priority.</html>"
        );
        welcome.setFont(new Font("SansSerif", Font.BOLD, 34));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
            "<html>Book your tickets, manage reservations,<br>"
            + "and make every journey simple.</html>"
        );
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setForeground(new Color(200, 210, 230));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(brand);
        leftPanel.add(Box.createVerticalStrut(95));
        leftPanel.add(trainIcon);
        leftPanel.add(Box.createVerticalStrut(22));
        leftPanel.add(welcome);
        leftPanel.add(Box.createVerticalStrut(18));
        leftPanel.add(description);

        // RIGHT SIDE
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EmptyBorder(65, 55, 45, 55));

        JLabel heading = new JLabel("Welcome back");
        heading.setFont(new Font("SansSerif", Font.BOLD, 29));
        heading.setForeground(TEXT);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Sign in to continue to your account");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel usernameLabel = new JLabel("Username");
        styleLabel(usernameLabel);

        usernameField = new JTextField();
        styleField(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        styleLabel(passwordLabel);

        passwordField = new JPasswordField();
        styleField(passwordField);

        loginButton = new JButton("Sign In");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(BLUE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        loginButton.setPreferredSize(new Dimension(300, 46));

        JLabel footer = new JLabel("Secure access to your reservations");
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(MUTED);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(heading);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(subtitle);
        rightPanel.add(Box.createVerticalStrut(38));

        rightPanel.add(usernameLabel);
        rightPanel.add(Box.createVerticalStrut(8));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createVerticalStrut(22));

        rightPanel.add(passwordLabel);
        rightPanel.add(Box.createVerticalStrut(8));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(30));

        rightPanel.add(loginButton);
        rightPanel.add(Box.createVerticalStrut(28));
        rightPanel.add(footer);

        // LOGIN BUTTON ACTION
       
        // LOGIN BUTTON ACTION
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getPassword();

            try {
                if (username.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter both username and password.",
                        "Missing Details",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

                try (Connection con = DatabaseConnection.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, username);
                    ps.setString(2, new String(password));

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String role = rs.getString("role");

                            JOptionPane.showMessageDialog(
                                this,
                                "Login successful! Welcome, " + username
                                    + "\nRole: " + role,
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                this,
                                "Invalid username or password.",
                                "Login Failed",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + ex.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } finally {
                java.util.Arrays.fill(password, '\0');
                passwordField.setText("");
            }
        });


        // ENTER KEY ALSO SUBMITS THE FORM
        getRootPane().setDefaultButton(loginButton);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
        setVisible(true);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(TEXT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(BACKGROUND);
        field.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(220, 225, 235)
                ),
                new EmptyBorder(10, 12, 10, 12)
            )
        );
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        field.setPreferredSize(new Dimension(300, 44));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
