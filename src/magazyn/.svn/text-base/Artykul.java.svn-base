/**
 * klasa opisujaca typ jakim jest Artykul bedacy na stanie magazynu, przechowuje
 * wszystkie inforamcje o obiekcie,
 */
package magazyn;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marcin Iwaniuk
 *
 */
public class Artykul {

    private int ilosc;
    private int EAN;
    private int polka;
    private int regal;
    private String jednostka;
    private String nazwa;
    DataBase query = new DataBase();
    String sql;
    //konstrktor

    Artykul() {
        ilosc = 0;
        /**
         * @param ilosc Okeśla bierzącą ilość sztuk produktu na stanie magazynu
         */
        EAN = 000000000;
        /**
         * @param EAN Określa 9 znaków definiujących kod EAN produktu
         */
        polka = 0;
        /**
         * @param polka Określa numer półki na której można odszukać artykuł
         */
        regal = 0;
        /**
         * @param regal Określa numer regału na którym można odszukać artykuł
         */
        jednostka = "";
        /**
         * @param jednostka miary
         */
        nazwa = "";
        /**
         * @param nazwa Nazwa określająca artykuł
         */
    }

    /**
     * @param ilosc
     * @param EAN
     * @param polka
     * @param regal
     * @param nazwa
     * @param jednostka
     * @return Metoda dodająca artykuł do bazy danych, zwraca string będący
     * wynikiem zapytania SQL.
     * @throws SQLException
     */
    public String dodaj_nowy_artykul(int ilosc, int EAN, int polka, int regal, String nazwa, String jednostka) {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT INTO `artykul` (`ilosc_a`, `ean_a`, `polka_a`, `regal_a`, `nazwa_a`, `jednostka_a`) VALUES ('" + ilosc + "','" + EAN + "','" + polka + "','" + regal + "','" + nazwa + "','" + jednostka + "');";
            try {
                st.executeQuery("USE " + DataBase.baza + ";");
                st.executeUpdate(sql);
                wynik = "Dodano artykuł";
                return sql;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    ;
   /**
     * Metoda dodawania sztuk istniejącego artykułu. Wykorzystywana przez PZ
     * @param sztuk
     * @param EAN
     * @return String z wynikiem zapytania SQL
     */
 public String dodaj_sztuk(int sztuk, int EAN) {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "UPDATE `artykul` SET `ilosc_a`=`ilosc_a`+" + sztuk + " WHERE `ean_a`=" + EAN + ";";
            try {
                st.executeQuery("USE " + DataBase.baza + ";");
                st.executeUpdate(sql);
                wynik = "Dodano przedmiot";
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    ;
 /**
  * Metoda usuwania sztuk istniejącego w bazie danych artykułu. Wykorzystywana przez WZ
  * @param sztuk
  * @param EAN
  * @return
  */
 public String usun_sztuk(int sztuk, int EAN) {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            st.execute("USE " + DataBase.baza + ";");
            sql = "UPDATE `artykul` SET `ilosc_a`=`ilosc_a`-" + sztuk + " WHERE `ean_a`=" + EAN + ";";
            try {
                st.executeQuery("USE " + DataBase.baza);
                st.executeUpdate(sql);
                wynik = "Usunięto przedmiot";
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    ;
 /**
  * Metoda wyszukiwania artykuły po nazwie
  * @param nazwa
  * @return Stringa jak wyniku zapytania SQL, z wartościami poszeczególnych kolumn i tabel.
  */
 public String get_art(String nazwa) {
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE " + DataBase.baza + ";");
                ResultSet rs = st.executeQuery("SELECT * FROM `artykul` WHERE `nazwa_a` LIKE '" + nazwa + "';");
                while (rs.next()) {
                    String liczba = rs.getString("ilosc_a");
                    String ean = rs.getString("ean_a");
                    String polka = rs.getString("polka_a");
                    String regal = rs.getString("regal_a");
                    String name = rs.getString("nazwa_a");
                    String jm = rs.getString("jednostka_a");
                    String out = ("Artykul: " + name + "\nEAN: " + ean + "\nLiczba: " + liczba + "\nPółka: " + polka + "\nRegał: " + regal + "\tJednotska miary: " + jm);
                    return out;
                    //System.out.println("Artyukul" + sztuk);
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }
        return "";
    }

    /**
     *
     * @param ean
     * @return Stringa jak wyniku zapytania SQL, z wartościami poszeczególnych
     * kolumn i tabel.
     */
    public String get_art_ean(int ean) {
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE " + DataBase.baza + ";");
                ResultSet rs = st.executeQuery("SELECT * FROM `artykul` WHERE `ean_a`=" + ean + ";");
                while (rs.next()) {
                    String liczba = rs.getString("ilosc_a");
                    String ean_a = rs.getString("ean_a");
                    String polka = rs.getString("polka_a");
                    String regal = rs.getString("regal_a");
                    String name = rs.getString("nazwa_a");
                    String jm = rs.getString("jednostka_a");
                    String out = ("Artykuł: " + name + "\nEAN: " + ean + "\nLiczba: " + liczba + "\nPółka: " + polka + "\nRegał: " + regal + "\tJednotska miary: " + jm);
                    return out;
                    //System.out.println("Artyukul" + sztuk);
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }
        return "";
    }
}
