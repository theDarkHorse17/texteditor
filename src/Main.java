import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends JPanel implements ActionListener {
    //components
    JTextArea area=new JTextArea();
    JSpinner spnr=new JSpinner();
    JScrollPane pane=new JScrollPane(area);
    JLabel font =new JLabel("Font: ");
    JButton color=new JButton("Color");
    String []f =GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    JComboBox FontbBox=new JComboBox(f);

    JMenuBar menuBar=new JMenuBar();
    JMenu file=new JMenu("File");
    JMenuItem openitem=new JMenuItem("Open");
    JMenuItem saveitem=new JMenuItem("Save");
    JMenuItem exit=new JMenuItem("Exit");

    //componentspecofication
    Main(){
        this.setLayout(new FlowLayout());
        //this.add(area);
        this.setBackground(Color.white);
        area.setLineWrap(true);
        area.setFont(new Font("Gilroy",Font.PLAIN,20));
       // area.setPreferredSize(new Dimension(450,450));
        area.setBackground(Color.white);
        pane.setPreferredSize(new Dimension(450,450));
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        spnr.setPreferredSize(new Dimension(50,25));
        spnr.setValue(20);
        spnr.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                area.setFont(new Font(area.getFont().getFamily(),Font.PLAIN, (Integer) spnr.getValue()));
            }
        });
        color.addActionListener(this);
        FontbBox.setSelectedItem("Arial");
        FontbBox.addActionListener(this);

        //--------menubar--------//
       file.add(openitem);
       file.add(saveitem);
       file.add(exit);
       menuBar.add(file);


       openitem.addActionListener(this);
       saveitem.addActionListener(this);
       exit.addActionListener(this);
        //--------menubar--------//
        this.add(menuBar);
        this.add(font);
        this.add(spnr);
        this.add(color);
        this.add(FontbBox);
        this.add(pane);


    }
    public static void main(String[] args) {
        JFrame frame=new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        Main ain=new Main();
        frame.add(ain);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==color){
            JColorChooser colorchoose=new JColorChooser();
            Color color=colorchoose.showDialog(null,"Choose Color",Color.black);
            area.setForeground(color);
        }
        if(e.getSource()==FontbBox){
            area.setFont(new Font((String)FontbBox.getSelectedItem(),
                    Font.PLAIN,
                    area.getFont().getSize()));
        }
        if(e.getSource()==openitem){
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter=new FileNameExtensionFilter("Text Files","txt");
            fileChooser.setFileFilter(filter);

            int response=fileChooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner filein=null;

                try{
                    filein=new Scanner(file);
                    if(file.isFile()){
                        while(filein.hasNextLine()){
                            String line= filein.nextLine()+"\n";
                            area.append(line);
                        }
                    }

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                finally {
                    filein.close();
                }
            }
        }
        if(e.getSource()==saveitem){
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int response= fileChooser.showSaveDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                File file;
                PrintWriter fileOut=null;

                file =new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut=new PrintWriter(file);
                    fileOut.println(area.getText());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                finally {
                    fileOut.close();
                }
            }
        }
        if(e.getSource()==exit){
            System.exit(0);
        }
    }
}
