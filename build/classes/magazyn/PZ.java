/**
 * zarzadzanie dokumentami PZ (przyjecie zewnetrzne)
 */
package magazyn;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Marcin Iwaniuk
 */
public class PZ {

    /**
     * kontrachent, od kogo, co, ile.
     */
    int od_kogo;
    int przedmiot;
    int ilosc;
    int dokum;
    DataBase query = new DataBase();
    String sql;

    PZ() {
        od_kogo = 0;
        /**
         * @param od_kogo id dostawcy produktu
         */
        przedmiot = 000000000;
        /**
         * @param przedmiot kod ean towaru
         */
        ilosc = 0;
        /**
         * @param ilosc ilość sztuk towaru na dok pz towaru
         */
        dokum = 0;
        /**
         * @param dokum numer dokumentu PZ, dzieki temu mozemy miec wiele
         * artykulow w jednym dokumencie PZ
         */
    }

    /**
     * Dodaje dokument PZ do bazy danych.
     *
     * @param od_kogo id dostawcy,
     * @param EAN kod ean produktu
     * @param ilosc ilosc sztuk danego produktu dla danego artukulu
     * @param dokum_nr numer dokumentu PZ
     */
    public String dodaj_pz(int od_kogo, int item_id, int ilosc, int dokum_nr) {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT INTO `pz` (`od_kogo_p`, `przedmiot_p`, `ilosc_p`, `nr_dok_p`) VALUES ('" + od_kogo + "','" + item_id + "','" + ilosc + "','" + dokum_nr + "');";

            try {
                st.executeQuery("USE " + DataBase.baza);
                st.executeUpdate(sql);
                wynik = "Dodano dokument PZ";
                Artykul art = new Artykul();
                art.dodaj_sztuk(ilosc, item_id);
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

    /**
     * Wyszukuje PZ do numerze dokumentu
     *
     * @param dok
     * @return Informacje o zawartosci dokumentu PZ
     */
    public String szukaj_pz(int dok) {
        String wynik = "";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "SELECT `nazwa_k`,`nazwa_a`,`ean_a`,`ilosc_p`,`nr_dok_p` FROM `pz`, `kontrachent`, `artykul` WHERE `nr_dok_p`='" + dok + "' AND od_kogo_p = id_k AND `przedmiot_p` = `id_a;";
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String kogo = rs.getString("nazwa_k");
                    String co = rs.getString("nazwa_a");
                    int ean = Integer.parseInt(rs.getString("ean_a"));
                    String liczba = rs.getString("ilosc_p");
                    String dokum = rs.getString("nr_dok_p");
                    wynik = ("PZ:\nKontrachent: " + kogo + "\nPrzedmiot: " + co + "\nIlość: " + liczba + "\nNr dokumentu: " + dokum);
                    return wynik;
                }
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
}
