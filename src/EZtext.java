import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EZtext implements ActionListener {

    JFrame frame;
    JTextArea textArea;
    JButton save;
    JButton clear;
    Scanner scan;

    EZtext() throws IOException {

        Border border = BorderFactory.createLineBorder(Color.cyan, 5);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\KULDEEP\\IdeaProjects\\notepod\\TheIcon.png");

        textArea = new JTextArea();
        textArea.setBorder(border);
        textArea.setCaretColor(Color.cyan);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.cyan);
        textArea.setFont(textArea.getFont ().deriveFont (20.0f));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(1, 1, 584, 325);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        save = new JButton("save file");
        save.addActionListener(this);
        save.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        save.setBounds(5, 330, 100, 30);
        save.setBackground(Color.GRAY);
        save.setFocusable(false);
        save.setBorderPainted(false);

        clear = new JButton("clear file");
        clear.addActionListener(this);
        clear.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
        clear.setBounds(480, 330, 100, 30);
        clear.setBackground(Color.GRAY);
        clear.setFocusable(false);
        clear.setBorderPainted(false);

        File file = new File("NoteData.txt");
        if (file.createNewFile()) {
            System.out.println("file created");
        } else {
            System.out.println("file already exists");
        }
        scan = new Scanner(file);

        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent += scan.nextLine() + "\n";
        }

        textArea.setText(fileContent);

        frame = new JFrame("...NotePod...");
        frame.setResizable(false);
        frame.setIconImage(icon);
        frame.add(save);
        frame.add(clear);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String data = textArea.getText();
        if (e.getSource() == save) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("NoteData.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                assert fileWriter != null;
                fileWriter.write(data);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (e.getSource() == clear) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("NoteData.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                assert fileWriter != null;
                fileWriter.write("");
                textArea.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
