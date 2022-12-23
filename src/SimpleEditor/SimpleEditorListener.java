package SimpleEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleEditorListener implements ActionListener {

    SimpleEditor editor;
    private String operator;
    private JTextArea text;
    File file;

    SimpleEditorListener(String operator, SimpleEditor editor, JTextArea text) {
        this.editor = editor;
        this.operator = operator;
        this.text = text;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (operator) {

            case "open": {
                //editor.open();
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int res = chooser.showOpenDialog(editor);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    editor.setFile(file);
                    try {
                        if (file.getAbsolutePath().endsWith(".txt")) {
                            String fileString = null;
                            if (file.canRead()) {
                                try {
                                    FileReader reader = new FileReader(file);
                                    BufferedReader bufferedReader = new BufferedReader(reader);
                                    StringBuilder builder = new StringBuilder();
                                    String line = bufferedReader.readLine();
                                    while (line != null) {
                                        builder.append(line);
                                        builder.append(System.lineSeparator());
                                        line = bufferedReader.readLine();
                                    }
                                    fileString = builder.toString();
                                    bufferedReader.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(SimpleEditor.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println(file + " не может быть прочтен.");
                                }
                            }
                            text.setText(fileString);
                        }
                    } catch (Exception ex) {
                        System.out.println("Выберите файл с расширением txt");
                    }
                }

                break;
            }
            case "save": {
                this.file = editor.getFile();
                String text1 = text.getText();
                if (file == null) return;
                if (file.canWrite()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(text1);
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SimpleEditor.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(file + " не может быть записан.");
                    }
                }
                break;
            }
            case "cancel": {
                file = null;
                text.setText(null);
                break;
            }
            case "exit": {
                int res = JOptionPane.showConfirmDialog(editor, "Сохранить файл?", "Save", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    this.file = editor.getFile();
                    String text1 = text.getText();
                    if (file == null) return;
                    if (file.canWrite()) {
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write(text1);
                            writer.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SimpleEditor.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println(file + " не может быть записан.");
                        }
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }
                    break;
                }
            }
        }
    }
}
