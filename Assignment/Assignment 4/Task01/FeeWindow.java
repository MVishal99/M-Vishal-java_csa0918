import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

/** AWT view: collects input and displays service results. */
public class FeeWindow extends Frame {
    private final TextField regNo = new TextField(18);
    private final TextField amount = new TextField(18);
    private final Label status = new Label("Enter fee details.");
    private final FeeService service;

    public FeeWindow(Connection connection) {
        super("Student Fee Payment");
        service = new FeeService(connection);

        Panel fields = new Panel(new GridLayout(2, 2, 6, 6));
        fields.add(new Label("Register number:"));
        fields.add(regNo);
        fields.add(new Label("Amount:"));
        fields.add(amount);

        Button pay = new Button("Pay");
        Button clear = new Button("Clear");
        Button receipt = new Button("Receipt");
        Panel actions = new Panel();
        actions.add(pay);
        actions.add(clear);
        actions.add(receipt);

        add(fields, BorderLayout.NORTH);
        add(actions, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        pay.addActionListener(e -> {
            try {
                service.pay(regNo.getText(), amount.getText());
                status.setText("Payment recorded successfully.");
            } catch (IllegalArgumentException | java.sql.SQLException ex) {
                status.setText(ex.getMessage());
            }
        });
        clear.addActionListener(e -> {
            regNo.setText("");
            amount.setText("");
            status.setText("Fields cleared.");
        });
        receipt.addActionListener(e -> showReceipt());
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(420, 190);
    }

    private void showReceipt() {
        Dialog dialog = new Dialog(this, "Receipt", true);
        dialog.add(new Label("Receipt details for: " + regNo.getText()));
        Button close = new Button("Close");
        close.addActionListener(e -> dialog.dispose());
        dialog.add(close, BorderLayout.SOUTH);
        dialog.setSize(280, 110);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
