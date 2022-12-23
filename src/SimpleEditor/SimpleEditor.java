package SimpleEditor;


import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SimpleEditor extends JFrame {
    //SimpleEditorFileManager fileEdit;
    private JLabel label;
    private JTextArea text;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem Open, Save, Cancel, Exit;
    File file;


    public SimpleEditor () {
        setTitle("Текстовый редактор");
        setSize(480,480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container root = getContentPane();
        root.setLayout(new GridLayout(1,1));

        label = new JLabel();
        text = new JTextArea();
        bar = new JMenuBar();
        setJMenuBar(bar);
        menu = new  JMenu("File");
        bar.add(menu);
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Cancel = new JMenuItem("Cancel");
        Exit = new JMenuItem("Exit");

        root.add(text, BorderLayout.CENTER);

        menu.add(Open);
        menu.add(Save);
        menu.add(Cancel);
        menu.add(Exit);

        Open.addActionListener(new SimpleEditorListener("open", this, text));
        Save.addActionListener(new SimpleEditorListener("save", this, text));
        Cancel.addActionListener(new SimpleEditorListener("cancel", this, text));
        Exit.addActionListener(new SimpleEditorListener("exit", this, text));

        setVisible(true);

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}



