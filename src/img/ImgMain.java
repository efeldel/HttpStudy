package img;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            URL url = new URL("https://n1s1.elle.ru/48/7b/36/487b36300c62c5f0cb905da52aa874b4/728x486_1_30b570c2f6c0da65bb56095068e05768@940x627_0xc0a839a4_18087198581488362059.jpeg");
            Image image = ImageIO.read(url);
            g.drawImage(image, 20,20, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ImgMain {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setBounds(100, 100, 800, 600);
        window.getContentPane().add(new MyPanel());
        window.setVisible(true);
    }
}
