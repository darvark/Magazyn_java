/*
 * Klasa tworzenia splashscreen'u
 */
package magazyn;

import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {

    private int duration;

    public SplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        int width = 450;
        int height = 300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width)/ 2;
        int y = (screen.height - height)/2;
        setBounds(x, y, width, height);

        // Tworzy splashscreen
        JLabel label = new JLabel(new ImageIcon("images/icon.png"));
        JLabel copyrt = new JLabel("Copyright 2012, Marcin Iwaniuk", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(187, 228, 47, 100);//red green blue, alpha
        content.setBorder(BorderFactory.createLineBorder(oraRed, 2));

        //Wyswietla
        setVisible(true);

        // Odczekaj chwile
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }

        setVisible(false);

    }

    public void showSplashAndExit() {

        showSplash();
        System.exit(0);

    }
}