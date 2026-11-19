import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** Keeps validation and payment persistence outside the GUI. */
public class FeeService {
    private final Connection connection;

    public FeeService(Connection connection) {
        this.connection = connection;
    }

    public void pay(String regNo, String amountText) throws SQLException {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Register number is required.");
        }
        final double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Enter a valid amount.");
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        String sql = "INSERT INTO fees(reg_no, amount) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, regNo.trim());
            ps.setDouble(2, amount);
            ps.executeUpdate();
        }
    }
}
