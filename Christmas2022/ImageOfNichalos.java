
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class ImageOfNichalos extends JFrame {
    private JButton playButton, stopButton, resetButton;
    private File file;
    private Clip clip;
    private BufferedImage image1, image2;
    private JLabel label1, label2;
    private JTextArea msgTextArea;
    private JFrame f;
    private JPanel controlPanel;
    public String returnMessage() {
        String gender, name = JOptionPane.showInputDialog("Enter your Name : " );
        do {
            gender = JOptionPane.showInputDialog("Enter the gender (male / Female): ");
            if(!(gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female"))){
                JOptionPane.showConfirmDialog(null, "Wrong Input", "Error", JOptionPane.OK_OPTION);
            }
            else {
                break;
            }
        }while(true);
        String message = "Merry Christmas to " + name+",";
        if(gender.equalsIgnoreCase("female")) {
            message += " May God Bless You,\n All The Happyiness in The Universe,\n My favourite princess!";
        }
        else {
            message += " May God Bless You,\n All The Happyiness in The Universe,\n My favourite prince!";
        }
        return message+"\t---St.Nicholas.";
    }
    public void drawTheWindow() {
        file = new File("./music/James-Lord-Pierpont-Jingle-Bells-.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        f = new JFrame();
        controlPanel = new JPanel();
        JPanel buttonPanel = createButtons();
        JPanel imagePanel = createImages();
        String msg = returnMessage();
        JPanel messagePanel = createMessage(msg);
        
        controlPanel.add(imagePanel);
        controlPanel.add(buttonPanel);
        controlPanel.add(messagePanel);
    
        f.add(controlPanel);
        f.setSize(1000,850);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JPanel createMessage(String msg) {
        int RATE = 12;
        msgTextArea = new JTextArea(3,30);
        msgTextArea.setFont(new Font("Serif", Font.ITALIC,25));
        msgTextArea.setText(msg);
        msgTextArea.setEditable(false);
        
        JPanel p = new JPanel();
        p.add(msgTextArea);
        return p;
    }
    private JPanel createImages() {
        try {
            image1 = ImageIO.read(new File("./image/christmasTree.jpg"));
            image2 = ImageIO.read(new File("./image/SaintNicholas.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
        label1 = new JLabel(new ImageIcon(image1));
        label2 = new JLabel(new ImageIcon(image2));
        JPanel p = new JPanel();
        p.add(label1);
        p.add(label2);
        return p;
    }

    class PlayListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clip.start();
        }
    }
    class StopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clip.stop();
            
        }
        
    }
    class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clip.setMicrosecondPosition(0);
            
        }    
    }
    
    public JPanel createButtons() {
        playButton = new JButton("Play");
        playButton.addActionListener(new PlayListener());
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopListener());
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetListener());
        
        JPanel panel = new JPanel();
        panel.add(playButton);
        panel.add(resetButton);
        panel.add(stopButton);
        return panel;
    }
    
}
