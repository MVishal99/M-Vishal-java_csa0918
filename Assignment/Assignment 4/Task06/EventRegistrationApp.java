import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Compact Swing + JDBC sample. Configure DB_URL, DB_USER and DB_PASSWORD. */
public class EventRegistrationApp extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/campus_events";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "change_me";

    private final JTextField studentId = new JTextField();
    private final JTextField studentName = new JTextField();
    private final JComboBox<String> event = new JComboBox<>(
            new String[]{"Tech Fest", "Cultural Day", "Sports Meet"});
    private final JComboBox<String> filter = new JComboBox<>(
            new String[]{"All", "Tech Fest", "Cultural Day", "Sports Meet"});
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Student ID", "Name", "Event", "Registered At"}, 0);
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");

    public EventRegistrationApp() {
        super("Campus Event Registration");
        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        form.add(new JLabel("Student ID")); form.add(studentId);
        form.add(new JLabel("Student name")); form.add(studentName);
        form.add(new JLabel("Event")); form.add(event);
        JButton register = new JButton("Register");
        JButton refresh = new JButton("Filter / Refresh");
        form.add(register); form.add(refresh);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter event:"));
        filterPanel.add(filter);
        top.add(filterPanel, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        menu.add(exit); menuBar.add(menu); setJMenuBar(menuBar);

        register.addActionListener(e -> registerAsync());
        refresh.addActionListener(e -> loadAsync());
        loadAsync();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 420);
        setLocationRelativeTo(null);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void registerAsync() {
        String sid = studentId.getText().trim();
        String name = studentName.getText().trim();
        String eventName = (String) event.getSelectedItem();
        if (sid.isEmpty() || name.isEmpty()) {
            status.setText("Student ID and name are required.");
            return;
        }
        new SwingWorker<Void, Void>() {
            @Override protected Void doInBackground() throws Exception {
                String sql = "INSERT INTO registrations(student_id, student_name, event_name) VALUES (?, ?, ?)";
                try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setString(1, sid); ps.setString(2, name); ps.setString(3, eventName);
                    ps.executeUpdate();
                }
                return null;
            }
            @Override protected void done() {
                try { get(); status.setText("Registration successful."); loadAsync(); }
                catch (Exception ex) { status.setText("Registration failed: duplicate or database error."); }
            }
        }.execute();
    }

    private void loadAsync() {
        String selected = (String) filter.getSelectedItem();
        new SwingWorker<Object[][], Void>() {
            @Override protected Object[][] doInBackground() throws Exception {
                String sql = "SELECT registration_id, student_id, student_name, event_name, registered_at FROM registrations"
                        + ("All".equals(selected) ? "" : " WHERE event_name = ?")
                        + " ORDER BY registration_id DESC LIMIT 500";
                try (Connection c = connect(); PreparedStatement ps = c.prepareStatement(sql)) {
                    if (!"All".equals(selected)) ps.setString(1, selected);
                    try (ResultSet rs = ps.executeQuery()) {
                        java.util.List<Object[]> rows = new java.util.ArrayList<>();
                        while (rs.next()) rows.add(new Object[]{rs.getLong(1), rs.getString(2),
                                rs.getString(3), rs.getString(4), rs.getTimestamp(5)});
                        return rows.toArray(new Object[0][]);
                    }
                }
            }
            @Override protected void done() {
                try {
                    model.setRowCount(0);
                    for (Object[] row : get()) model.addRow(row);
                    status.setText("Loaded " + model.getRowCount() + " registrations.");
                } catch (Exception ex) { status.setText("Could not load registrations: " + ex.getMessage()); }
            }
        }.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
