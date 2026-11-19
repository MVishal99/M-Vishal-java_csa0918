import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Nested BorderLayout/FlowLayout panels let the results area grow on resize. */
public class LibrarySearchScreen extends JFrame {
    private final JTextField searchText = new JTextField(22);
    private final JComboBox<String> category =
            new JComboBox<>(new String[]{"All", "Books", "Journals", "Theses"});
    private final JTextArea results = new JTextArea();

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Library Search");
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(title);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchText);
        searchPanel.add(category);
        JButton search = new JButton("Search");
        searchPanel.add(search);

        JPanel north = new JPanel(new BorderLayout());
        north.add(titlePanel, BorderLayout.NORTH);
        north.add(searchPanel, BorderLayout.SOUTH);
        add(north, BorderLayout.NORTH);

        results.setEditable(false);
        results.setText("Search results will appear here.");
        add(new JScrollPane(results), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.add(new JButton("Prev"));
        bottom.add(new JButton("Next"));
        bottom.add(new JButton("Reserve"));
        bottom.add(new JLabel("Ready"));
        add(bottom, BorderLayout.SOUTH);

        search.addActionListener(e -> results.setText(
                "Searching for: " + searchText.getText()
                + " | Category: " + category.getSelectedItem()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchScreen().setVisible(true));
    }
}
