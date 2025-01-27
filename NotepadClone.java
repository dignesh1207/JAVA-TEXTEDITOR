package TEXTEDITOR;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class NotepadClone extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextArea textArea;
    private JFileChooser fileChooser;
    
    public NotepadClone() {
        setTitle("Notepad Clone");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new javax.swing.plaf.FontUIResource("Arial", 0, 16));
        add(new JScrollPane(textArea));
        
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        
        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu editMenu = new JMenu("Edit");
        JMenuItem findReplaceItem = new JMenuItem("Find & Replace");
        findReplaceItem.addActionListener(e -> showFindReplaceDialog());
        editMenu.add(findReplaceItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);
    }

    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showFindReplaceDialog() {
        JDialog findReplaceDialog = new JDialog(this, "Find & Replace", true);
        findReplaceDialog.setSize(400, 200);
        findReplaceDialog.setLayout(new javax.swing.BoxLayout(findReplaceDialog.getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        JLabel findLabel = new JLabel("Find:");
        JTextField findField = new JTextField();
        JLabel replaceLabel = new JLabel("Replace:");
        JTextField replaceField = new JTextField();
        JButton replaceButton = new JButton("Replace All");

        replaceButton.addActionListener(e -> {
            String findText = findField.getText();
            String replaceText = replaceField.getText();
            if (!findText.isEmpty()) {
                textArea.setText(textArea.getText().replace(findText, replaceText));
            }
        });

        findReplaceDialog.add(findLabel);
        findReplaceDialog.add(findField);
        findReplaceDialog.add(replaceLabel);
        findReplaceDialog.add(replaceField);
        findReplaceDialog.add(replaceButton);

        findReplaceDialog.setLocationRelativeTo(this);
        findReplaceDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotepadClone().setVisible(true));
    }
}
