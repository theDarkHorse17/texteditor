import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class texteditor extends JPanel implements ActionListener {
    JTextArea textArea;
    texteditor(){

       this.setSize(500,500);
       this.setLayout(new FlowLayout());
       textArea=new JTextArea();

       this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
