import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class client implements ActionListener {

    JTextField jTextField;
    JButton jButton;
   static JPanel jp;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;


     client(){

         JPanel panel = new JPanel();
         panel.setBackground(Color.white);
         panel.setLayout(null);
         f.setSize(350, 550);
         f.setTitle("TELEGRAM");
         f.add(panel);
         f.setVisible(true);
         f.setLocation(200, 100);

         JPanel p1 = new JPanel();
         p1.setBounds(0, 0, 340, 60);
         p1.setBackground(new Color(32, 138, 229));
         p1.setLayout(null);
         panel.add(p1);


         ImageIcon i1 = new ImageIcon("Arro.png");
         Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         JLabel back = new JLabel(i3);
         back.setBounds(5, 20, 25, 25);
         p1.add(back);

         ImageIcon i4 = new ImageIcon("caIcon1.png");
         Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
         ImageIcon i6 = new ImageIcon(i5);
         JLabel profile = new JLabel(i6);
         profile.setForeground(new Color(32, 138, 229));
         profile.setBounds(40, 10, 50, 50);
         p1.add(profile);

         JLabel label1 = new JLabel("x.....");
         label1.setBounds(110, 15, 200, 15);
         label1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
         label1.setForeground(Color.white);
         p1.add(label1);

         JLabel label2 = new JLabel("Active Now");
         label2.setBounds(110, 33, 200, 15);
         label2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
         label2.setForeground(Color.white);
         p1.add(label2);


         ImageIcon i7 = new ImageIcon("video.png");
         Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
         ImageIcon i9 = new ImageIcon(i8);
         JLabel video = new JLabel(i9);
         video.setBounds(200, 20, 30, 30);
         p1.add(video);

         ImageIcon i10 = new ImageIcon("phone.png");
         Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
         ImageIcon i12 = new ImageIcon(i11);
         JLabel phone = new JLabel(i12);
         phone.setBounds(260, 20, 35, 30);
         p1.add(phone);


         ImageIcon i13 = new ImageIcon("icon.png");
         Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
         ImageIcon i15 = new ImageIcon(i14);
         JLabel Dots = new JLabel(i15);
         Dots.setBounds(310, 20, 10, 25);
         p1.add(Dots);


         jTextField = new JTextField();
         jTextField.setBounds(0, 480, 210, 30);
         panel.add(jTextField);

         jButton = new JButton("send");
         jButton.setBounds(220, 480, 100, 30);
         jButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
         jButton.setFocusable(false);
         jButton.setBackground(new Color(32, 138, 229));
         jButton.addActionListener(this);
         panel.add(jButton);


         jp = new JPanel();
         jp.setBounds(0, 70, 340, 400);
         panel.add(jp);

         back.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 System.exit(0);
             }
         });
     }
    public static void main(String args[]){
        new client();
        try {
            Socket s = new Socket("localhost", 6008);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while(true) {
                jp.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                jp.add(vertical, BorderLayout.PAGE_START);

                f.validate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String out = jTextField.getText();

            JPanel p2 = formatLabel(out);

            jp.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            jp.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            jTextField.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception E) {
            E.printStackTrace();


        }
    }
    public static JPanel formatLabel (String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 70px\">" + out + "</p></html>");
        output.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        output.setBackground(new Color(32, 138, 229));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);


        return panel;
    }

}
