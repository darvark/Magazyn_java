/**
 * Zarządzanie materiałami w magazynie(dodawanie, usuwanie, modyfikacja),
 * składanie zamówień, odbieranie materiałów, lokalizacja w magazynie,
 * dostarczanie brakujących materiałów, itp..
 */
package magazyn;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marcin Iwaniuk
 */
public class Magazyn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SplashScreen splash = new SplashScreen(3000);
        splash.showSplash();

        File_Parser plik = new File_Parser();

        try {
            plik.parse();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracja.class.getName()).log(Level.SEVERE, null, ex);
        }
        Mag main_win = new Mag();
        main_win.setIconImage(Toolkit.getDefaultToolkit().getImage("images/icon.png"));
        main_win.setTitle("Magazyn - system zarządzania magazynem");
        main_win.setVisible(true);

    }
}
